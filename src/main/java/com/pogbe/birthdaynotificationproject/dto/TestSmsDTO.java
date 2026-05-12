package com.pogbe.birthdaynotificationproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestSmsDTO {
    private String recipientContact;
    private String message;
    private String applicationPassword;
}
