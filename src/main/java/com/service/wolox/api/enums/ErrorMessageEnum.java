package com.service.wolox.api.enums;

public enum ErrorMessageEnum {

    USER_NOT_FOUND("User with id: %s was not found"),
    ERROR_FINDING_USER("Error finding user with id: %s");

    private String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
