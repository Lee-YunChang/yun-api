package com.yunapi.controller;

import com.yunapi.dto.MemberDto;
import com.yunapi.entity.Member;
import com.yunapi.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BasicController {

    @Autowired
    BasicService basicService;


    @GetMapping("/memberList")  //List select
    public ResponseEntity<List<Member>> memberList(){
        List<Member> memberList = basicService.memberList();

        return  ResponseEntity.ok().body(memberList);
     }

    @GetMapping("/{id}")    //Id로 select
    public ResponseEntity<Member> member(@PathVariable("id") long id){
        Member member  = basicService.member(id);
        return ResponseEntity.ok().body(member);
    }

    @PostMapping("/save")   //Save
    public ResponseEntity<?> save(@RequestBody MemberDto memberDto){

        return ResponseEntity.ok().body(basicService.save(memberDto));
    }

    @PostMapping("/delete/{id}")  //Delete
    public ResponseEntity<?> delete(@PathVariable("id") long id){
       return  ResponseEntity.ok().body(basicService.delete(id));
    }

    @PostMapping("/update/{id}")    //Update
    public ResponseEntity<?> update(@PathVariable("id") long id,@RequestBody MemberDto memberDto){
       return ResponseEntity.ok().body(basicService.update(id,memberDto));
    }

}
