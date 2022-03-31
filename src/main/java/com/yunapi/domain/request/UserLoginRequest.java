package com.yunapi.domain.request;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserLoginRequest {
    private Long id;
    private String token;
    
    private String email;
    private String securityCode;
    private String uuid;
    
    private String device;
    private int loginType = 1;
    private String forcedLogin;
    @Hidden private String userIp;
}
