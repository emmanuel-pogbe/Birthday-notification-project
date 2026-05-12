package com.pogbe.birthdaynotificationproject.dto.smsrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sms {
    private Auth auth;
    private Message message;
    private Recipients recipients;
    private int dndsender;
}
