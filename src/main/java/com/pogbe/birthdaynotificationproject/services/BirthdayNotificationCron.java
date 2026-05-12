package com.pogbe.birthdaynotificationproject.services;

import com.pogbe.birthdaynotificationproject.models.User;
import com.pogbe.birthdaynotificationproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BirthdayNotificationCron {
    private final UserRepository userRepository;

    public BirthdayNotificationCron(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(fixedDelay = 43200000) // so should run every 12 hours after a run
    public void notifyUsersOnTheirBirthday() {
        try {
            List<Long> userIdsNotified = new ArrayList<>();
            List<User> birthdayUsers = userRepository.findAllByDateOfBirthAndEligibleForNotification();
            // query users whose birthday is today
            // some logic for notifying those users
            for (User user : birthdayUsers) {
                String phoneNumber = user.getPhoneNumber();
                String fullName = user.getFullName();
                String message = "Happy Birthday " + fullName + "!\nYou are now " + " years old";

                System.out.println("Sending message to " + phoneNumber);
                System.out.println(message);
                userIdsNotified.add(user.getId());
            }

            userRepository.updateYearNotified(userIdsNotified);
            // when all the users have been notified, gather all IDs and set all their yearsNotified to Year.now
        } catch (Exception e) {
            log.error("Error notifying users on their birthday: {}", e.getMessage());
        }
    }

}
