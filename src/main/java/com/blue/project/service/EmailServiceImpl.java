package com.blue.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender javaMailSender;

    public void sendMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("DO-NOT-REPLY@tjctechnology.com");

        this.javaMailSender.send(message);
    }

    @Override
    public void sendTemplateMessage(String to, String subject, SimpleMailMessage template, String ...templateArgs) {
        String text = String.format(template.getText(), templateArgs);
        sendMessage(to, subject, text);
    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
        helper.setFrom("DO-NOT-REPLY@tjctechnology.com");

        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);

        this.javaMailSender.send(message);
    }
}
