package com.service.wolox.api.config.error;

import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.error.ErrorMessage;
import com.service.wolox.api.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ApiWoloxException.class)
    public ResponseEntity<ErrorMessage> apiWoloxException(ApiWoloxException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDetailError(ex.getMessage());
        errorMessage.setSource(ex.getSource());
        errorMessage.setDate(Utils.createDate());
        return new ResponseEntity<>(errorMessage, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exception(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDetailError(ex.getMessage());
        errorMessage.setDate(Utils.createDate());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
