package com.blake.ticketmasterdemo.model.vo.base;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BaseWebReqHeader {
    @NotBlank
    private String transReqTime;
}
