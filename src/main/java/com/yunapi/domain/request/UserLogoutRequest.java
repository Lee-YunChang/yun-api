package com.yunapi.domain.request;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserLogoutRequest {
    private Long id;
    private String uuid;
    private String device;
    @Hidden private String userIp;
}
