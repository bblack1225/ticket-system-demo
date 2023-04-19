package com.blake.ticketmasterdemo.service;

import com.blake.ticketmasterdemo.enums.ResponseStatus;
import com.blake.ticketmasterdemo.model.vo.base.BaseResponse;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResHeader;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.utils.DateUtils;
import lombok.val;
import org.slf4j.MDC;


public abstract class CommonService {
    protected <T extends BaseResponse> BaseWebResponse<T> packaging(T res){
        val successType = ResponseStatus.SUCCESS;
        val header = BaseWebResHeader.builder()
                .transId(MDC.get("transId"))
                .statusCode(successType.getStatusCode())
                .statusDesc(successType.getStatusDesc())
                .transResTime(DateUtils.getCurrentTimeStr())
                .build();
        return new BaseWebResponse<>(header, res);
    }
}
