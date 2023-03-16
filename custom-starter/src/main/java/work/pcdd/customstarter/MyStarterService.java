package work.pcdd.customstarter;

import org.springframework.beans.BeanUtils;

/**
 * @author pcdd
 * @date 2021/5/10 8:14
 */
public class MyStarterService {
    private final MyStarterProperties properties;

    public MyStarterService(MyStarterProperties properties) {
        this.properties = properties;
    }

    public MyStarterProperties sayHello() {
        MyStarterProperties myStarterProperties = new MyStarterProperties();
        BeanUtils.copyProperties(properties, myStarterProperties, "enable");
        return myStarterProperties;
    }
}
