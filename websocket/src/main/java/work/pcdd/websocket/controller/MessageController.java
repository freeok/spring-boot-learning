package work.pcdd.websocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.websocket.socket.MessageEntity;
import work.pcdd.websocket.socket.MessageWebsocketServer;

/**
 * @author pcdd
 * @date 2021/8/2 17:11
 */
@RestController
@RequestMapping("/ws")
@RequiredArgsConstructor
public class MessageController {

    private final MessageWebsocketServer messageWebsocketServer;

    /**
     * 给指定用户推送消息
     */
    @GetMapping("/{username}/{msg}")
    public void send(@PathVariable String username, @PathVariable String msg) {
        messageWebsocketServer.sendOne(username, msg);
    }

    /**
     * 给所有用户推送消息
     */
    @PostMapping("/all")
    public void sendAll(@RequestBody MessageEntity entity) {
        messageWebsocketServer.onMessage(entity);
    }

}
