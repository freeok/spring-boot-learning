package work.pcdd.async.service;

/**
 * @author pcdd
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
