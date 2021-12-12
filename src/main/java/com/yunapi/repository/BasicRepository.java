package com.yunapi.repository;

import com.yunapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasicRepository extends JpaRepository<Member, Long> {
	
	List<Member> findAll();

	Member findById(long id);

}
