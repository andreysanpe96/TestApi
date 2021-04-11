package com.service.wolox.api.enums.user;

import com.service.wolox.api.enums.ErrorEnumInterface;

public enum UserErrorEnum implements ErrorEnumInterface {

    USER_NOT_FOUND("User with id: %s was not found"),
    NOT_USERS("Not users found"),
    ERROR_FINDING_ALL("Error finding all users"),
    ERROR_FINDING_USER("Error finding user with id: %s");

    private String message;

    UserErrorEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
