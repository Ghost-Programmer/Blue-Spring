package com.nrha.reinersuite.service;

import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMessage(String to, String subject, String text);
    void sendTemplateMessage(String to, String subject, SimpleMailMessage template, String ...templateArgs);
    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException;
}
