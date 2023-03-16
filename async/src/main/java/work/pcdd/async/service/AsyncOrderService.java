package work.pcdd.async.service;

/**
 * @author PC
 */
public interface AsyncOrderService {
    /**
     * 短信服务
     */
    void sendSms();

    /**
     * 邮件服务
     */
    void sendEmail();
}
