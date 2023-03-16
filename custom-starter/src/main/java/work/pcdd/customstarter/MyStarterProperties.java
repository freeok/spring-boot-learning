package work.pcdd.customstarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author pcdd
 * @date 2021/5/10 8:12
 */

@Data
@ConfigurationProperties(prefix = "pcdd.mystarter")
public class MyStarterProperties {
    /**
     * 是否开启自定义starter功能
     */
    private Boolean enable = true;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private String sex = "M";
}
