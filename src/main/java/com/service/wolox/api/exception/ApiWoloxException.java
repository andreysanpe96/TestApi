package com.service.wolox.api.exception;


import com.service.wolox.api.enums.ErrorMessageEnum;
import org.springframework.http.HttpStatus;

public class ApiWoloxException extends Exception{

    private HttpStatus status;
    private String source;

    public ApiWoloxException(String message, HttpStatus status, String source){
        super(message);
        this.status = status;
        this.source = source;
    }

    public ApiWoloxException(ErrorMessageEnum error, HttpStatus status, String source){
        super(error.getMessage());
        this.status = status;
        this.source = source;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
