package com.pogbe.birthdaynotificationproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    private String fullName;
    private String phoneNumber;
    private String email;
    private LocalDate dateOfBirth;
}
