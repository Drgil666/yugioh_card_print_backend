package project.yugioh.card_print.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import project.yugioh.card_print.service.MailService;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * @author Gilbert
 * @date 2022/3/25 21:42
 */
@Service
public class MailServiceImpl implements MailService {
    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String userName;
    @Value("${export.pdf.name}")
    private String exportPdfName;

    /**
     * 发送邮件
     *
     * @param file  附件
     * @param email 邮件地址
     */
    @Override
    public void sendMail(File file, String email) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // true表示构建一个可以带附件的邮件对象
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("红龙打印机导出文档");
        helper.setFrom(userName);
        helper.setTo(email);
        helper.setSentDate(new Date());
        helper.setText("感谢您使用红龙打印机！\r\n如有缺印漏印卡图缺失或bug反馈等，请联系作者邮箱：2574105038@qq.com.\r\n感谢您的支持!\r\n    By DrGilbert");
        // 第一个参数是自定义的名称，后缀需要加上，第二个参数是文件的位置
        helper.addAttachment(exportPdfName, file);
        javaMailSender.send(mimeMessage);
    }
}
