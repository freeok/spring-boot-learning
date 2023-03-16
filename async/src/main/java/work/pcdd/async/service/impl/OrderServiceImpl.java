package work.pcdd.async.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.pcdd.async.service.AsyncOrderService;
import work.pcdd.async.service.OrderService;

/**
 * @author pcdd
 * @date 2021/8/17 2:48
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private AsyncOrderService asyncOrderService;

    @Override
    public void makeOrder() {
        // 保存订单
        saveOrder();
        // 发短信
        asyncOrderService.sendSms();
        // 发邮件
        asyncOrderService.sendEmail();
    }

    @Override
    public void saveOrder() {
        log.info("生成订单并保存...");
    }
}
