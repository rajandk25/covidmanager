package com.manage.cov.covman.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSender {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    //send Email using cov man account to someone else
    public void sendMail(String to, String msg, String subject) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(to);
            helper.setText(msg);
            helper.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
