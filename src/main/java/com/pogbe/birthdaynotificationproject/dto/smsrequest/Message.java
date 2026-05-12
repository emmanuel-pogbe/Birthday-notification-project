package com.pogbe.birthdaynotificationproject.dto.smsrequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String sender;
    private String messagetext;
    private String flash;
}
