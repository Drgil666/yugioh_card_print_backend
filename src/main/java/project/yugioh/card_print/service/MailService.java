package project.yugioh.card_print.service;

import javax.mail.MessagingException;
import java.io.File;

/**
 * @author Gilbert
 * @date 2022/3/25 21:38
 */
public interface MailService {
    /**
     * 发送邮件
     *
     * @param file  附件
     * @param email 邮件地址
     * @throws MessagingException 发送邮件异常
     */
    void sendMail(File file, String email) throws MessagingException;
}

