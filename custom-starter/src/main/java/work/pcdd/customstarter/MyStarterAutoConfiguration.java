package work.pcdd.customstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pcdd
 * @date 2021/5/10 8:18
 */
@Configuration
// 使标注@ConfigurationProperties注解的类生效。
@EnableConfigurationProperties(MyStarterProperties.class)
// 某个class位于类路径上，才会实例化一个Bean
@ConditionalOnClass(MyStarterService.class)
// prefix:配置文件中key的前缀，可与value 或 name 组合使用
// value:该属性与下面的 name 属性不可同时使用，当value所对应配置文件中的值为false时，注入不生效，不为false注入生效;
// value有多个值时，只要有一个值对应为false,则注入不成功
// matchIfMissing:该属性为true时，配置文件中缺少对应的value或name的对应的属性值，也会注入成功
// havingValue:与value 或 name 组合使用，只有当value 或 name 对应的值与havingValue的值相同时，注入生效
@ConditionalOnProperty(prefix = "pcdd.mystarter", value = "enable", matchIfMissing = true)
public class MyStarterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(MyStarterService.class)
    public MyStarterService myStarterService(MyStarterProperties properties) {
        return new MyStarterService(properties);
    }

}
