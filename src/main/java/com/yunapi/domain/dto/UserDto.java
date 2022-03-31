package com.yunapi.domain.dto;


import com.yunapi.entity.User;
import com.yunapi.util.DateUtils;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String birthDate;
    private String sex;
    private String securityCode;
    private String status;
    private String pushYn;
    private String tokenKey;
    private String appVersion;
    private String ci;
    private String uuid;
    private String deviceOs;
    private int biometrics;
    private String debug;
    private String recomm;
    private String recomm2;
    private String createTimestamp;
    
    public UserDto(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.email = u.getEmail();
        this.birthDate = u.getBirthDate();
        this.sex = u.getSex();
        this.securityCode = u.getSecurityCode();
        this.status = u.getStatus();
        this.pushYn = u.getPushYn();
        this.tokenKey = u.getTokenKey();
        this.appVersion = u.getAppVersion();
        this.ci = u.getCi();
        this.uuid = u.getUuid();
        this.deviceOs = u.getDeviceOs();
        this.biometrics = u.getBiometrics();
        this.debug = u.getDebug();
        this.recomm = u.getRecomm();
        this.recomm2 = u.getRecomm2();
        if(u.getCreateTimestamp() != null)
            this.createTimestamp = DateUtils.SDF.format(u.getCreateTimestamp());
    }
}
