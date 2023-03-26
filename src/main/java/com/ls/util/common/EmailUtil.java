package com.ls.util.common;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


public class EmailUtil {
    public void send_email(String to,String yzm) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");  //设置邮件服务器
        mailSender.setUsername("371471895@qq.com");
        mailSender.setPassword("srabqfqndqmgcbad");

        MimeMessage msg = mailSender.createMimeMessage();

        try{
            MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "utf-8");
            msgHelper.setTo(to);
            msgHelper.setFrom("371471895@qq.com");
            msgHelper.setSubject("便民生活平台");
            msgHelper.setText("\n" +
                     to+",您好！\n" +
                    "您正在注册便民生活平台，请在验证码输入框中输入： "+yzm+"，以完成操作。");

            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.timeout", "25000");
            mailSender.setJavaMailProperties(prop);

            mailSender.send(msg);
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
