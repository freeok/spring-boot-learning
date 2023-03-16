package pcdd.ltd.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Direct模式(直连型交换机)
 *
 * @author pc
 * @date 2022-02-14 11:41:27
 */
@Configuration
public class DirectRabbitConfig {
    /**
     * 1 声明注册交换机direct模式的交换机
     */
    @Bean
    DirectExchange loginLogDirectExchange() {
        return new DirectExchange("loginLog.direct");
    }

    /**
     * 2 声明队列 loginLog.direct.queue
     */
    @Bean
    public Queue loginLogDirectQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        // return new Queue("loginLogDirectQueue",true,true,false);
        // 一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("loginLog.direct.queue");
    }

    /**
     * 3 完成绑定关系（队列和交换机完成绑定关系），并设置用于匹配键：loginLogRouting
     */
    @Bean
    Binding bindingDirect() {
        return BindingBuilder
                .bind(loginLogDirectQueue())
                .to(loginLogDirectExchange())
                .with("loginLog");
    }
}
