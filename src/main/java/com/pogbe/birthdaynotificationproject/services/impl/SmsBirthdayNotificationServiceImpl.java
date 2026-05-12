package com.pogbe.birthdaynotificationproject.services.impl;

import com.pogbe.birthdaynotificationproject.dto.smsrequest.*;
import com.pogbe.birthdaynotificationproject.dto.smsresponse.SmsResponseDTO;
import com.pogbe.birthdaynotificationproject.services.interfaces.BirthdayNotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class SmsBirthdayNotificationServiceImpl implements BirthdayNotificationService {
    @Value("${ebulksms.base_url}")
    private String smsBaseUrl;
    @Value("${ebulksms.key}")
    private String apiKey;
    @Value("${ebulksms.username}")
    private String username;

    @Override
    public void sendNotification(String recipientContact, String message) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        SmsObjectDTO smsObjectDTO = new SmsObjectDTO();
        Sms sms = Sms.builder()
                // this is in dire need of refactoring
                // this just builds a nested JSON structure that the SMS api expects which is unfortunately complicated
                .auth(new Auth(username, apiKey))
                .message(new Message("HM ltd",message,"0"))
                .recipients(new Recipients(List.of(new MsgIds(recipientContact, UUID.randomUUID().toString()))))
                .dndsender(0)
                .build();
        smsObjectDTO.setSMS(sms);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON, MediaType.valueOf("text/json")));

        try {
            String requestBody = objectMapper.writeValueAsString(smsObjectDTO);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        String smsUrl = smsBaseUrl + "/sendsms.json";
            ResponseEntity<String> smsResponseDTO = restTemplate.exchange(smsUrl, HttpMethod.POST, request, String.class);

            if (smsResponseDTO != null && smsResponseDTO.getStatusCode().is2xxSuccessful() && smsResponseDTO.getBody() != null) {
                SmsResponseDTO body = objectMapper.readValue(smsResponseDTO.getBody(), SmsResponseDTO.class);
                if (body.getResponse() != null) {
                    String status = body.getResponse().getStatus();
                    String totalSent = body.getResponse().getTotalSent();
                    String cost = body.getResponse().getCost();
                    log.info("SMS API response - status: {}, totalSent: {}, cost: {}", status, totalSent, cost);
                } else {
                    log.warn("SMS API returned 2xx but response body has no 'response' field: {}", smsResponseDTO.getBody());
                }
            } else {
                log.error("Failed SMS API call. Status: {} Body: {}", smsResponseDTO != null ? smsResponseDTO.getStatusCode() : "null", smsResponseDTO != null ? smsResponseDTO.getBody() : "null");
            }
        } catch (Exception e) {
            log.error("Error sending SMS notification", e);
        }
    }
}
