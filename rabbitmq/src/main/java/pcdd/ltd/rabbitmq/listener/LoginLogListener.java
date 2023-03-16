package pcdd.ltd.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author pc
 * @date 2022-02-14 11:45:31
 */
@Slf4j
@Component
public class LoginLogListener {
    @RabbitListener(queues = "loginLog.direct.queue")
    public void receiveMessage(Map<String, Object> msg, Channel channel, Message message) {
        // 只包含发送的消息
        log.info("参数1 接收消息：{}", msg);
        // channel 通道信息
        log.info("参数2 Channel：{}", channel);
        // message 附加的参数信息
        log.info("参数3 Message：{}", message);
    }
}
