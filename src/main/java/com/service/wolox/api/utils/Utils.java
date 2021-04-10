package com.service.wolox.api.utils;

import com.service.wolox.api.enums.ErrorMessageEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String createDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String createErrorMessageWithId(ErrorMessageEnum errorMessageEnum, long id){
        return String.format(errorMessageEnum.getMessage(), id);
    }
}
