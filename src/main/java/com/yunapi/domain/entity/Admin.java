package com.yunapi.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Entity
@Where(clause = "status = 'Y'")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;
	
	@Column(length = 40)
	private String username;
	
	private String password;
	
	@Column(length = 50)
	private String name;
	
	@Column(length = 1)
	@ColumnDefault(value = "'Y'")
	private String status;
	
	@Column(length = 50)
	private String sessionId;	

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createTimestamp;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Timestamp updateTimestamp;
	
	@OneToMany
	@JoinColumn(name = "admin_id")
	private List<AdminRole> roles;
	
	private Integer err;
	
	@PrePersist
	protected void prePersist() {
		this.status = "Y";
		this.err = 0;
		this.createTimestamp = Timestamp.valueOf(LocalDateTime.now());
	}
	
}