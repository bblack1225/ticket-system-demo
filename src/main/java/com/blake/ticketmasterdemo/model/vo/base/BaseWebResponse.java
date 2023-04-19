package com.blake.ticketmasterdemo.model.vo.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseWebResponse<T extends BaseResponse> {

    private BaseWebResHeader header;

    private T transResp;
}
