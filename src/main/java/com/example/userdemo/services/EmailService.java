package com.example.userdemo.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendNotification(String email, String action) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("no-reply@yourwebsite.com");

        if ("CREATE".equals(action)) {
            message.setSubject("Аккаунт создан");
            message.setText("Здравствуйте! Ваш аккаунт на сайте успешно создан.");
        } else if ("DELETE".equals(action)) {
            message.setSubject("Аккаунт удален");
            message.setText("Здравствуйте! Ваш аккаунт был удалён.");
        }
        mailSender.send(message);
    }
}
