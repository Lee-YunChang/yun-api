package com.yunapi.controller;

import com.yunapi.entity.Member;
import com.yunapi.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BasicController {

    @Autowired
    BasicService basicService;

    @GetMapping("/")
    public String hello(){
        return "Hello World!";
    }


    @GetMapping("/member")
    public List<Member> memberList(){
        return basicService.memberList();
     }
}
