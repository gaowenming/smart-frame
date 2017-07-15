package com.smart.server.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Author: gaowenming
 * Description:
 * Date: Created in 18:11 2017/7/2.
 */
@Component
public class SmartMailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromUser;

    /**
     * 发送简单邮件
     *
     * @param toUser  收件人
     * @param subject 主题
     * @param content 内容
     * Async 异步调用
     */
    public void sendSimpleMail(String toUser, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromUser);
        message.setTo(toUser);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}
