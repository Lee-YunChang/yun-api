package com.yunapi.domain.request;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class UserJoinRequest {
    private String name;
    private String birthDate;
    private String phoneNumber;
    private String sex;
    private String email;
    private String pw;
    private String ci;
    private String di;
    private String pushYn;
    private String device;
    private String appVersion;
    private String appVersionCode;
    private String recomm2;
    private String foreigner;
    private String uuid;
    @Hidden private String userIp;
}
