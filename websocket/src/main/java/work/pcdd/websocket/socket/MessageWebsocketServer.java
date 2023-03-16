package work.pcdd.websocket.socket;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pcdd
 * @date 2021/8/2 17:15
 */
@Slf4j
@Component
@ServerEndpoint(value = "/{name}", encoders = JsonEncoder.class, decoders = JsonDecoder.class)
public class MessageWebsocketServer {

    /**
     * 记录当前在线连接数
     */
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger();

    /**
     * 存放每个客户端对应的WebSocketServer对象。
     */
    private static final Map<String, Session> SESSION_POOLS = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "name") String username) {
        SESSION_POOLS.put(username, session);
        // 在线连接数自增1
        ONLINE_COUNT.incrementAndGet();
        log.info("{}加入WebSocket！当前人数为{}", username, ONLINE_COUNT);
        sendOnlineCount();
    }

    @OnClose
    public void onClose(@PathParam(value = "name") String username) {
        ONLINE_COUNT.decrementAndGet();
        log.info("{}断开WebSocket连接！当前人数为 {}", username, ONLINE_COUNT);
        // 将掉线的用户移除在线的组里
        SESSION_POOLS.remove(username);
        sendOnlineCount();
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 收到客户端消息时触发
     */
    @OnMessage
    public void onMessage(MessageEntity entity) {
        sendAll(entity);
    }

    /**
     * 单发消息
     */
    public void sendOne(String username, String message) {
        Session session = SESSION_POOLS.get(username);
        if (session != null) {
            MessageEntity messageEntity = MessageEntity.builder()
                    .type(MessageType.GROUP_CHAT)
                    .username(username)
                    .msg(message)
                    .build();
            session.getAsyncRemote().sendObject(messageEntity);
        }
    }

    /**
     * 群发消息
     */
    private void sendAll(MessageEntity entity) {
        entity.setCreateTime(LocalDateTime.now());
        log.info("向客户端群发消息：{}", entity);
        for (Map.Entry<String, Session> sessionEntry : SESSION_POOLS.entrySet()) {
            Session session = sessionEntry.getValue();
            session.getAsyncRemote().sendObject(entity);
        }
    }

    /**
     * 发送在线人数
     */
    private void sendOnlineCount() {
        MessageEntity messageEntity = MessageEntity.builder()
                .type(MessageType.SYSTEM)
                .username("admin")
                .msg(ONLINE_COUNT.toString())
                .createTime(LocalDateTime.now())
                .build();
        sendAll(messageEntity);
    }

}
