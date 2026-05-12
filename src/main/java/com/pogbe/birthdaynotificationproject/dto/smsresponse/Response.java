package com.pogbe.birthdaynotificationproject.dto.smsresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    @JsonProperty("status")
    private String status;

    // API returns "totalsent" (lowercase) in the JSON; map it to camelCase field
    @JsonProperty("totalsent")
    private String totalSent;

    @JsonProperty("cost")
    private String cost;
}
