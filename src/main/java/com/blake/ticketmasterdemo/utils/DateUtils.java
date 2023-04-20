package com.blake.ticketmasterdemo.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private DateUtils(){}

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static String localDateTimeToStr(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime dateStrToLocalDateTime(String dateStr) {
        if(StringUtils.isBlank(dateStr)){
            return null;
        }
        return LocalDateTime.parse(dateStr, DATE_TIME_FORMATTER);
    }
}
