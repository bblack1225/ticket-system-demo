package com.blake.ticketmasterdemo.enums;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    SUCCESS("0000", "Success"),
    ARTIST_EXIST_ERROR_0001("0001", "Artist already exists"),
    ARTIST_NOT_EXIST_ERROR_0002("0002", "Artist does not exist"),
    EVENT_NAME_EXIST_ERROR_0003("0003", "Event name already exists");

    private final String statusCode;
    private final String statusDesc;

    ResponseStatus(String statusCode, String statusDesc) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }
}
