package com.pogbe.birthdaynotificationproject.dto.smsrequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipients {
    private List<MsgIds> gsm;
}
