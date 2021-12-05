package com.yunapi.service;

import com.yunapi.entity.Member;
import com.yunapi.repository.BasicRepository;
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
