package pcdd.ltd.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pcdd
 * @date 2022-02-14 11:39:47
 */
@RestController
public class ProducerController {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public String index() {
        Map<String, Object> map = new LinkedHashMap<>(3);
        map.put("id", ATOMIC_INTEGER.getAndIncrement());
        map.put("name", "pcdd");
        map.put("age", "21");
        rabbitTemplate.convertAndSend("loginLog.direct", "loginLog", map);
        return "Send successfully";
    }
}
