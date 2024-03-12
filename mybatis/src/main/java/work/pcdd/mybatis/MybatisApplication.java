package work.pcdd.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <a href="https://mp.weixin.qq.com/s/zW7lwLPkWTp7Z6m-qFasig">MyBatis 教程</a>
 *
 * @author pcdd
 */
@MapperScan("work.pcdd.mybatis.mapper")
@SpringBootApplication
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
