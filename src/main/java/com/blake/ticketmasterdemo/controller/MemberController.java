package com.blake.ticketmasterdemo.controller;


import com.blake.ticketmasterdemo.enums.MemberStatus;
import com.blake.ticketmasterdemo.enums.ResponseStatus;
import com.blake.ticketmasterdemo.exception.ServiceException;
import com.blake.ticketmasterdemo.model.entity.Member;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.model.vo.member.CreateMemberRequest;
import com.blake.ticketmasterdemo.model.vo.member.CreateMemberResponse;
import com.blake.ticketmasterdemo.model.vo.member.UpdateMemberRequest;
import com.blake.ticketmasterdemo.repository.MemberRepository;
import com.blake.ticketmasterdemo.service.CreateMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @PostMapping("/update")
    public String update(@RequestBody UpdateMemberRequest request){
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new ServiceException(ResponseStatus.UNKNOWN_ERROR_0099));
        member.setStatus(request.getStatus());
        memberRepository.save(member);
        return "Success";
    }

    @Transactional
    @GetMapping("/getMember")
    public String getMember(@RequestParam int userId){
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ResponseStatus.UNKNOWN_ERROR_0099));
        log.info("Member status: {}", member.getStatus());

        // 模擬在第二次讀取之前，數據庫中的資料發生了變化
        try {
            Thread.sleep(5000); // 延遲 5 秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Member member2 =  memberRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ResponseStatus.UNKNOWN_ERROR_0099));
        log.info("Member2 status: {}", member2.getStatus());
        return "Success";
        }

    }
