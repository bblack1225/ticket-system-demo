package com.blake.ticketmasterdemo.model.vo.member;

import com.blake.ticketmasterdemo.model.vo.base.BaseResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMemberResponse implements BaseResponse {
    private Integer userId;
    private String email;
    private String userName;
}
