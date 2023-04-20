package com.blake.ticketmasterdemo.enums;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    // COMMON STATUS 0XXX
    // ARTIST 1XXX
    // EVENT 2XXX
    // MEMBER 3XXX
    // TICKET 4XXX
    SUCCESS("0000", "Success"),
    UNKNOWN_ERROR_0099("0099", "Unknown error"),
    ARTIST_EXIST_ERROR_1001("1001", "Artist already exists"),
    ARTIST_NOT_EXIST_ERROR_1002("1002", "Artist does not exist"),
    EVENT_NAME_EXIST_ERROR_2001("2001", "Event name already exists"),
    EVENT_NOT_EXIST_ERROR_2002("2002", "Event does not exist"),
    EVENT_NOT_AT_SALE_TIME_ERROR_2003("2003", "Event is not at sale time"),

    USER_NOT_EXIST_ERROR_3001("3001", "User does not exist"),
    USER_EMAIL_EXIST_ERROR_3002("3002", "User email already exists"),

    TICKET_SOLD_OUT_ERROR_4001("4001", "Ticket sold out"),
    TICKET_MAX_PER_USER_ERROR_4002("4002", "Ticket max per user");

    private final String statusCode;
    private final String statusDesc;

    ResponseStatus(String statusCode, String statusDesc) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }
}
