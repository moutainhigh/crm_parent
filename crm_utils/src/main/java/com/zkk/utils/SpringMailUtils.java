package com.zkk.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/16 15:16
 */
@Component("mailUtils")
public class SpringMailUtils {
    @Resource(name = "mailMessage")
    private SimpleMailMessage mailMessage;
    @Resource(name = "mailSender")
    private JavaMailSender mailSender;

    public void sendMail(String toAddress, String title, String text) {
        //设置收件人
        mailMessage.setTo(toAddress);
        //设置标题
        mailMessage.setSubject(title);
        //设置正文
        mailMessage.setText(text);
        //发送邮件
        mailSender.send(mailMessage);
    }
}
