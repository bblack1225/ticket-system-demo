package com.blake.ticketmasterdemo.model.vo.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseWebResHeader {
    private String transId;
    private String transResTime;
    private String statusCode;
    private String statusDesc;
}
