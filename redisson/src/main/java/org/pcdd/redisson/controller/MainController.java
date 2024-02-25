package org.pcdd.redisson.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author pcdd
 */
@Slf4j
@RestController
public class MainController {

    private static final String LOCK_KEY = "flash_sale_lock";
    private static final String PRODUCT_KEY = "product_1";
    private static final int PRODUCT_QUANTITY = 50;
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 从 Redis 中获取当前商品库存
     */
    private static int getCurrentQuantity(RedissonClient redisson) {
        Integer quantity = (Integer) redisson.getBucket(PRODUCT_KEY).get();
        return quantity != null ? quantity : PRODUCT_QUANTITY;
    }

    /**
     * 更新 Redis 中的商品库存
     */
    private static void updateQuantity(RedissonClient redisson, int newQuantity) {
        redisson.getBucket(PRODUCT_KEY).set(newQuantity);
    }

    /**
     * 分布式锁实现商品秒杀
     */
    @GetMapping("/")
    public String flashSale() {
        RLock rLock = redissonClient.getLock(LOCK_KEY);
        try {
            // 第一个参数：n 秒内获取不到锁，则返回 false
            // 第二个参数：m 秒后强制释放锁
            if (rLock.tryLock(10, 10, TimeUnit.SECONDS)) {
                // 获取当前商品库存
                int currentQuantity = getCurrentQuantity(redissonClient);
                if (currentQuantity > 0) {
                    // 如果库存大于0，进行秒杀操作
                    log.info("Flash sale success. Remaining quantity: {}", currentQuantity - 1);
                    // 更新商品库存
                    updateQuantity(redissonClient, currentQuantity - 1);
                    return "Flash sale success. Remaining quantity: " + (currentQuantity - 1);
                } else {
                    // 库存不足，秒杀失败
                    return "Flash sale failed. No remaining quantity.";
                }
            } else {
                // 获取锁失败，秒杀失败
                return "Flash sale failed. Unable to acquire lock.";
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        } finally {
            // 解锁前检查当前线程是否持有该锁
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
        return null;
    }

}
