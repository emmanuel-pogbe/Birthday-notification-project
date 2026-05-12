package com.pogbe.birthdaynotificationproject.services;

import com.pogbe.birthdaynotificationproject.dto.UserRegistrationDTO;
import com.pogbe.birthdaynotificationproject.mapper.UserRegistrationMapper;
import com.pogbe.birthdaynotificationproject.repository.UserRepository;
import com.pogbe.birthdaynotificationproject.utils.ValidatorUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRegistrationDTO.getFullName().isBlank()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }
        if (!ValidatorUtils.isValidEmail(userRegistrationDTO.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!ValidatorUtils.isValidPhoneNumber(userRegistrationDTO.getPhoneNumber())) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        userRepository.save(UserRegistrationMapper.toUser(userRegistrationDTO));
    }
}
