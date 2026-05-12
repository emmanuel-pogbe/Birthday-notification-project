package com.pogbe.birthdaynotificationproject.dto.smsrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    private String username;

    @JsonProperty("apikey")
    private String apiKey;
}
