package com.pogbe.birthdaynotificationproject.dto.smsrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsObjectDTO {
    private Sms SMS;
}
