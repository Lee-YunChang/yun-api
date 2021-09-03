package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BasicRepository extends JpaRepository<Member, Long> {
	
	List<Member> findAll();

}
