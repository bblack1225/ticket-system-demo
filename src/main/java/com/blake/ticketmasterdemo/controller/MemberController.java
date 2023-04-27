package com.blake.ticketmasterdemo.controller;


import com.blake.ticketmasterdemo.enums.MemberStatus;
import com.blake.ticketmasterdemo.model.entity.Member;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.model.vo.member.CreateMemberRequest;
import com.blake.ticketmasterdemo.model.vo.member.CreateMemberResponse;
import com.blake.ticketmasterdemo.repository.MemberRepository;
import com.blake.ticketmasterdemo.service.CreateMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final CreateMemberService createMemberService;

    private final MemberRepository memberRepository;

    @PostMapping("/create")
    public BaseWebResponse<CreateMemberResponse> createMember(
            @Valid @RequestBody BaseWebRequest<CreateMemberRequest> webRequest) {
        return createMemberService.execute(webRequest);
    }

    @PostMapping("/multipleCreate")
    public String test(){
        List<Member> memberList = new ArrayList<>();
        for(int i = 0; i < 90; i++){
            String passwordStr = "password";
            String emailPrefix = "black";
            String suffix = "@gmail.com";
            String usernamePrefix = "blake";
            Member member = new Member();
            member.setStatus(MemberStatus.ACTIVE);
            member.setPassword(passwordStr + i);
            member.setEmail(emailPrefix + i + suffix);
            member.setUsername(usernamePrefix + i);
            memberList.add(member);
        }
        memberRepository.saveAll(memberList);
        return "Success";
    }
}
