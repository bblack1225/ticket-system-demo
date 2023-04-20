package com.blake.ticketmasterdemo.service;

import com.blake.ticketmasterdemo.enums.MemberStatus;
import com.blake.ticketmasterdemo.enums.ResponseStatus;
import com.blake.ticketmasterdemo.exception.ServiceException;
import com.blake.ticketmasterdemo.model.entity.Member;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.model.vo.member.CreateMemberRequest;
import com.blake.ticketmasterdemo.model.vo.member.CreateMemberResponse;
import com.blake.ticketmasterdemo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateMemberService extends CommonService
            implements BaseApiService<CreateMemberRequest, CreateMemberResponse> {

    private final MemberRepository memberRepository;
    @Override
    public BaseWebResponse<CreateMemberResponse> execute(BaseWebRequest<CreateMemberRequest> webRequest) {
        val request = webRequest.getTransReq();
        if(memberRepository.existsByEmail(request.getEmail())){
            throw new ServiceException(ResponseStatus.USER_EMAIL_EXIST_ERROR_3002);
        }

        Member member = new Member();
        member.setEmail(request.getEmail());
        member.setPassword(request.getPassword());
        member.setUsername(request.getUsername());
        member.setStatus(MemberStatus.ACTIVE);
        member = memberRepository.save(member);

        val res = CreateMemberResponse.builder()
                .userId(member.getMemberId())
                .email(member.getEmail())
                .userName(member.getUsername())
                .build();
        return packaging(res);
    }
}
