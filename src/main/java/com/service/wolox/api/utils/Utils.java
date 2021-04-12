package com.service.wolox.api.utils;

import com.service.wolox.api.enums.ErrorEnumInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String createDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String createErrorMessageWithValue(ErrorEnumInterface userErrorEnum, Object id){
        return String.format(userErrorEnum.getMessage(), id);
    }
}
