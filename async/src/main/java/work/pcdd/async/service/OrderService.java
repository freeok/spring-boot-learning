package work.pcdd.async.service;

/**
 * @author pcdd
 */
public interface OrderService {
    /**
     * 下订单
     */
    void makeOrder();

    /**
     * 生成订单并保存到数据库
     */
    void saveOrder();
}
