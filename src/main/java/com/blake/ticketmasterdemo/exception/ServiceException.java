package com.blake.ticketmasterdemo.exception;

import com.blake.ticketmasterdemo.enums.ResponseStatus;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{
    private final String errorCode;
    private final String errorMsg;

    public ServiceException(ResponseStatus errorStatus) {
        this.errorCode = errorStatus.getStatusCode();
        this.errorMsg = errorStatus.getStatusDesc();
    }

    public ServiceException(ResponseStatus errorStatus, Throwable cause) {
        super(cause);
        this.errorCode = errorStatus.getStatusCode();
        this.errorMsg = errorStatus.getStatusDesc();
    }

    public ServiceException(ResponseStatus errorStatus, String customMsg) {
        this.errorCode = errorStatus.getStatusCode();
        this.errorMsg = customMsg;
    }
}
