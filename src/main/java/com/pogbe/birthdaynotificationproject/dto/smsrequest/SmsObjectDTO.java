package com.pogbe.birthdaynotificationproject.dto.smsrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsObjectDTO {
    @JsonProperty("SMS")
    private Sms SMS;
}
