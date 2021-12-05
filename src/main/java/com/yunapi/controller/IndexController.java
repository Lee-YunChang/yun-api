package com.yunapi.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index( ){

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }
}
