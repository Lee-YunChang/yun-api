package com.yunapi.service;

import com.yunapi.dto.MemberDto;
import com.yunapi.entity.Member;
import com.yunapi.repository.BasicRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BasicService {

    private final BasicRepository basicRepository;

    public List<Member> memberList(){

        return basicRepository.findAll();
    }

    public Member member(long id) {

        return basicRepository.findById(id);
    }

    public int save(MemberDto memberDto) {

        Member.MemberBuilder builder = Member.builder();

        builder.phone(memberDto.getPhone());
        builder.username(memberDto.getUsername());

       return basicRepository.save(builder.build()) != null ? 1 : 0;
    }

    public int delete(long id) {
      // return basicRepository.deleteById(id) != null ? 1: 0;
        return 0;
    }

    public int update(long id, MemberDto memberDto) {

        Member member = basicRepository.findById(id);

        member.setPhone(memberDto.getPhone());
        member.setUsername(memberDto.getUsername());
       return basicRepository.save(member) != null ? 1 : 0;
    }
}
