package com.yunapi.domain;

import com.yunapi.domain.entity.Admin;
import com.yunapi.domain.entity.AdminRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter @Setter
public class SecurityAdmin extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public SecurityAdmin(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public SecurityAdmin(Admin admin) {
		super(admin.getUsername(), admin.getPassword(), makeGrantedAuthority(admin.getRoles()));
		this.id = admin.getId();
		this.name = admin.getName();
	}
	
	private static List<GrantedAuthority> makeGrantedAuthority(List<AdminRole> roles){
		List<GrantedAuthority> list = new ArrayList<>();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(role.getRoleName())));
		return list;
	}
	
}

