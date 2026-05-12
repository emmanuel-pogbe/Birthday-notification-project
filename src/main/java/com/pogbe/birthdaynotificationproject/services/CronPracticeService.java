package com.pogbe.birthdaynotificationproject.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CronPracticeService {
//    @Scheduled(cron = "*/5 * * * * *")
    public void printMessage() {
        System.out.println("Cron job is running...");
    }
}
