package com.pogbe.birthdaynotificationproject.repository;

import com.pogbe.birthdaynotificationproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    // only notify users which their yearsNotified field is either null or not equals to Year.now
    @Query("SELECT u FROM User u WHERE MONTH(u.dateOfBirth) = MONTH(CURRENT_DATE()) AND DAY(u.dateOfBirth) = DAY(CURRENT_DATE()) AND (u.yearNotified IS NULL OR u.yearNotified != CAST(FUNCTION('YEAR', CURRENT_DATE()) AS String))")
    List<User> findAllByDateOfBirthAndEligibleForNotification();

    @Modifying
    @Query("UPDATE User u SET u.yearNotified = CAST(FUNCTION('YEAR', CURRENT_DATE()) AS String) WHERE u.id IN ?1")
    void updateYearNotified(List<Long> userId);
}
