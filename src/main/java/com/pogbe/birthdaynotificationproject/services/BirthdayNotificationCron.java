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

    @Scheduled(fixedDelay = 86400000) // so should ideally run everyday
    public void notifyUsersOnTheirBirthday() {
        try {
            List<Long> userIdsNotified = new ArrayList<>();
            LocalDate today = LocalDate.now();
            List<User> birthdayUsers = userRepository.findAllByDateOfBirthAndEligibleForNotification(today, today.getYear()+"" );
            // query users whose birthday is today
            // some logic for notifying those users
            for (User user : birthdayUsers) {
                String phoneNumber = user.getPhoneNumber();
                // notify the user using SMS -> Yet to implement
                userIdsNotified.add(user.getId());
            }

            userRepository.updateYearNotified(userIdsNotified, today.getYear()+"");
            // when all the users have been notified, gather all IDs and set all their yearsNotified to Year.now
        } catch (Exception e) {
            log.error("Error notifying users on their birthday: {}", e.getMessage());
        }
    }

}
