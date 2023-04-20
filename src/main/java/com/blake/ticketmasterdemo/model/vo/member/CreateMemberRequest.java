package com.blake.ticketmasterdemo.model.vo.member;

import com.blake.ticketmasterdemo.model.vo.base.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMemberRequest implements BaseRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String username;
}
