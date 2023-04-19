package com.blake.ticketmasterdemo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private DateUtils(){}

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static String getCurrentTimeStr() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime dateStrToLocalDateTime(String dateStr) {
        return LocalDateTime.parse(dateStr, DATE_TIME_FORMATTER);
    }
}
