package com.pogbe.birthdaynotificationproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private String errorCode;
    private String path;
    private OffsetDateTime timestamp;


    public ErrorResponse(String errorCode, String message, int value, String requestURI) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = value;
        this.path = requestURI;
        this.timestamp = OffsetDateTime.now();
    }
}
