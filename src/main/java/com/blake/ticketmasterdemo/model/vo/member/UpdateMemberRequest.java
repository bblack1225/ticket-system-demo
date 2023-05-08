package com.blake.ticketmasterdemo.model.vo.member;

import com.blake.ticketmasterdemo.enums.MemberStatus;
import lombok.Data;

@Data
public class UpdateMemberRequest {
    private int memberId;
    private MemberStatus status;
}
