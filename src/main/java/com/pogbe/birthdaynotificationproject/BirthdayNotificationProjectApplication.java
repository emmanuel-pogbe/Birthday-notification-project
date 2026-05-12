package com.pogbe.birthdaynotificationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BirthdayNotificationProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(BirthdayNotificationProjectApplication.class, args);
    }
}
