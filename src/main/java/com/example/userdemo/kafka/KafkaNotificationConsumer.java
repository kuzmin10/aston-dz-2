package com.example.userdemo.kafka;

import org.springframework.stereotype.Service;
import com.example.userdemo.services.EmailService;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class KafkaNotificationConsumer {
    private final EmailService emailService;

    public KafkaNotificationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user-notifications", groupId = "notification-group")
    public void listen(String message) {
        String[] parts = message.split(";");
        String action = parts[0];
        String email = parts[1];
        emailService.sendNotification(email, action);
    }
}
