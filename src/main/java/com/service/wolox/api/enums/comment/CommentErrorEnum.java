package com.service.wolox.api.enums.comment;

import com.service.wolox.api.enums.ErrorEnumInterface;

public enum CommentErrorEnum implements ErrorEnumInterface {

    COMMENT_NOT_FOUND("Comment with id: %s was not found"),
    NOT_COMMENT("Not comment found"),
    ERROR_FINDING_ALL("Error finding all comments"),
    ERROR_FINDING_COMMENT("Error finding comment with id: %s");

    private String message;

    CommentErrorEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
