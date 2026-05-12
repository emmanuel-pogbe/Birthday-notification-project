package com.pogbe.birthdaynotificationproject.controller;

import com.pogbe.birthdaynotificationproject.dto.TestSmsDTO;
import com.pogbe.birthdaynotificationproject.services.interfaces.BirthdayNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @Value("${application.password}")
    private String applicationPassword;

    private final BirthdayNotificationService birthdayNotificationService;

    public TestController(BirthdayNotificationService birthdayNotificationService) {
        this.birthdayNotificationService = birthdayNotificationService;
    }

    @PostMapping("/sms")
    public String testNotification(@RequestBody TestSmsDTO testSmsDTO) {
        if (!applicationPassword.equals(testSmsDTO.getApplicationPassword())) {
            throw new RuntimeException("Get outta here");
        }
        birthdayNotificationService.sendNotification(testSmsDTO.getRecipientContact(), testSmsDTO.getMessage());
        return "Message sent";
    }
}
