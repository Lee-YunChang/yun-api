package com.yunapi.domain.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
public class ExceptionHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", updatable = false, nullable = false)
    private long id;

    @Column(name="device_token")
    private String uuid;

    @Column(name="device_os")
    private String deviceOs;

    @Column(name="create_timestamp")
    private Timestamp createTimestamp;

    @Column(name="app_version")
    private String appVersion;

    @Column(name="app_version_code")
    private String appVersionCode;

    @Column(name="result_code")
    private String resultCode;

    @PrePersist
    protected void onCreate() {
        this.createTimestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public ExceptionHistory(String uuid, String deviceOs, String appVersion, String appVersionCode, String resultCode) {
        this.uuid = uuid;
        this.deviceOs = StringUtils.isBlank(deviceOs) ? "android" : deviceOs;
        this.appVersion = appVersion;
        this.appVersionCode = appVersionCode;
        this.resultCode = resultCode;
        this.createTimestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}