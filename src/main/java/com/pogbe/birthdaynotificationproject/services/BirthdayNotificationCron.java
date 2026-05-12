package com.pogbe.birthdaynotificationproject.services;

import com.pogbe.birthdaynotificationproject.models.User;
import com.pogbe.birthdaynotificationproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//    @Scheduled(fixedDelay = 43200000) // so should run every 12 hours after a run
    @Scheduled(fixedDelay = 10*1000) // this is every 10 seconds for testing
    @Transactional
    public void notifyUsersOnTheirBirthday() {
        try {
            List<Long> userIdsNotified = new ArrayList<>();
            List<User> birthdayUsers = userRepository.findAllByDateOfBirthAndEligibleForNotification();
            if (birthdayUsers.isEmpty()) {
                log.info("No users found with birthday today and not notified yet");
                return;
            }
            // query users whose birthday is today
            // some logic for notifying those users
            for (User user : birthdayUsers) {
                String phoneNumber = user.getPhoneNumber();
                String fullName = user.getFullName();
                int currentYear = LocalDate.now().getYear();
                int usersAge = currentYear - user.getDateOfBirth().getYear();
                String message = "Happy Birthday " + fullName + "!\nYou are now "+usersAge+" years old";

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
