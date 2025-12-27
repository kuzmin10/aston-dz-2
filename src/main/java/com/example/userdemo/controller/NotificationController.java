package com.example.userdemo.controller;


import com.example.userdemo.services.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public void sendManual(@RequestParam String email, @RequestParam String action) {
        emailService.sendNotification(email, action);
    }
}
