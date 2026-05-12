package com.pogbe.birthdaynotificationproject.dto.smsrequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgIds {
    private String msIdn;
    private String msgid;
}
