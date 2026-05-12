package com.pogbe.birthdaynotificationproject.mapper;

import com.pogbe.birthdaynotificationproject.dto.UserRegistrationDTO;
import com.pogbe.birthdaynotificationproject.models.User;

public class UserRegistrationMapper {
    public static User toUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setEmail(userRegistrationDTO.getEmail());
        user.setFullName(userRegistrationDTO.getFullName());
        user.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        user.setDateOfBirth(userRegistrationDTO.getDateOfBirth());
        return user;

    }
}
