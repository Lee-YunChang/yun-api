package com.yunapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DynamicInsert @DynamicUpdate
@Entity
@NoArgsConstructor
@Getter @Setter
@Where(clause = "status = 'Y'")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String email;
	
	private String birthDate;
	
	private String sex;
	
	private String securityCode;
	
	@Column(name="c_phone")
	private String phoneNumber;

	@Column(length = 2)
	@ColumnDefault("'Y'")
	private String status;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp createTimestamp;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp updateTimestamp;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp withdrawTimestamp;
	
	@Column(name= "pushyn")
	private String pushYn;
	
	private String tokenKey;
	
	private String appVersion;
	
	private String ci;
	
	private String di;
	
	private String uuid;
	
	private String deviceOs;
	
	private Integer biometrics;
	
	private String debug;
	
	private String recomm;
	
	private String recomm2;
	

	@PrePersist
	public void prePersist() {	
		this.status = "Y";
		this.createTimestamp = Timestamp.valueOf(LocalDateTime.now());
	}

	@PreUpdate
	protected void preUpdate() {
		this.updateTimestamp = Timestamp.valueOf(LocalDateTime.now());
	}

	@Builder
	public User(String name, String email, String birthDate, String sex, String securityCode, String phoneNumber, String status,
                String pushYn, String tokenKey, String appVersion, String ci, String di, String uuid, String deviceOs,
                Integer biometrics, String debug, String recomm, String recomm2) {

		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.sex = sex;
		this.securityCode = securityCode;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.pushYn = pushYn;
		this.tokenKey = tokenKey;
		this.appVersion = appVersion;
		this.ci = ci;
		this.di = di;
		this.uuid = uuid;
		this.deviceOs = deviceOs;
		this.biometrics = biometrics;
		this.debug = debug;
		this.recomm = recomm;
		this.recomm2 = recomm2;

	}
	
}