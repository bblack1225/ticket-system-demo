package com.blake.ticketmasterdemo.exception;


import com.blake.ticketmasterdemo.enums.ResponseStatus;
import com.blake.ticketmasterdemo.model.vo.base.BaseErrorResponse;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResHeader;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String TRANS_ID_KEY = "transId";

    @ExceptionHandler(value = ServiceException.class)
    public BaseWebResponse<BaseErrorResponse> handleServiceException(ServiceException e){
        if(e.getCause() != null){
            log.error("Error: ", e.getCause());
        }
        log.info("ServiceException: {}", e.getErrorMsg());
        val header = new BaseWebResHeader();
        header.setTransId(MDC.get(TRANS_ID_KEY));
        header.setTransResTime(DateUtils.localDateTimeToStr(LocalDateTime.now()));
        header.setStatusCode(e.getErrorCode());
        header.setStatusDesc(e.getErrorMsg());
        return new BaseWebResponse<>(header, new BaseErrorResponse());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public BaseWebResponse<BaseErrorResponse> handleRuntimeException(RuntimeException e){
        log.info("RuntimeException: ", e);
        val header = new BaseWebResHeader();
        val errorType = ResponseStatus.UNKNOWN_ERROR_0099;
        header.setTransId(MDC.get(TRANS_ID_KEY));
        header.setTransResTime(DateUtils.localDateTimeToStr(LocalDateTime.now()));
        header.setStatusCode(errorType.getStatusCode());
        header.setStatusDesc(errorType.getStatusDesc());
        return new BaseWebResponse<>(header, new BaseErrorResponse());
    }
}
