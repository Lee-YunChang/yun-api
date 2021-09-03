package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.repository.BasicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BasicService {

    private final BasicRepository basicRepository;

    public List<Member> memberList(){

        return basicRepository.findAll();
    }

}
