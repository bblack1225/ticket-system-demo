package com.blake.ticketmasterdemo.controller;


import com.blake.ticketmasterdemo.model.vo.base.BaseWebRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.model.vo.member.CreateMemberRequest;
import com.blake.ticketmasterdemo.model.vo.member.CreateMemberResponse;
import com.blake.ticketmasterdemo.service.CreateMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final CreateMemberService createMemberService;

    @PostMapping("/create")
    public BaseWebResponse<CreateMemberResponse> createMember(
            @Valid @RequestBody BaseWebRequest<CreateMemberRequest> webRequest) {
        return createMemberService.execute(webRequest);
    }
}
