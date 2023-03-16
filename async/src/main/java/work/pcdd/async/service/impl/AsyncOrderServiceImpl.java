package work.pcdd.async.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import work.pcdd.async.service.AsyncOrderService;

import java.util.concurrent.TimeUnit;

/**
 * @author PC
 * @version 1.0
 * @date 2021/8/17 2:48
 */
@Slf4j
@Service
public class AsyncOrderServiceImpl implements AsyncOrderService {
    @Async("asyncOrderService")
    @Override
    public void sendSms() {
        try {
            long start = System.currentTimeMillis();
            log.info("开始发送短信");
            // 模拟耗时3s
            TimeUnit.SECONDS.sleep(3);
            log.info("短信发送完毕！");
            log.info("短信发送完毕！耗时：{} ms", System.currentTimeMillis() - start);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async("asyncOrderService")
    @Override
    public void sendEmail() {
        try {
            long start = System.currentTimeMillis();
            log.info("开始发送邮件");
            // 模拟耗时2s
            TimeUnit.SECONDS.sleep(2);
            log.info("邮件发送完毕！耗时：{} ms", System.currentTimeMillis() - start);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
