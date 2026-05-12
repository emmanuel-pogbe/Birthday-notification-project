package com.pogbe.birthdaynotificationproject.repository;

import com.pogbe.birthdaynotificationproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    List<User> findAllByDateOfBirth(LocalDate dateOfBirth);

    // only notify users which their yearsNotified field is either null or not equals to Year.now
    @Query("SELECT u FROM User u WHERE u.dateOfBirth = ?1 AND (u.yearNotified IS NULL OR NOT u.yearNotified = ?2)")
    List<User> findAllByDateOfBirthAndEligibleForNotification(LocalDate DateOfToday, String yearNotified);

    @Modifying
    @Query("UPDATE User u SET u.yearNotified = ?2 WHERE u.id IN ?1")
    void updateYearNotified(List<Long> userId, String yearNotified);
}
