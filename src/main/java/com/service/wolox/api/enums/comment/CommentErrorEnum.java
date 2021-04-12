package com.service.wolox.api.enums.comment;

import com.service.wolox.api.enums.ErrorEnumInterface;

public enum CommentErrorEnum implements ErrorEnumInterface {

    COMMENT_NOT_FOUND("Comment with id: %s was not found"),
    NOT_COMMENT("Not comment found"),
    NOT_COMMENT_BY_USER("Not comment found with userId: %s"),
    NOT_COMMENT_BY_NAME("Not comment found with name: %s"),
    ERROR_FINDING_ALL("Error finding all comments"),
    NOT_QUERY_VALUES("Not values in filter params"),
    ERROR_FINDING_COMMENT_BY_USER("Error finding comment with userId: %s"),
    ERROR_FINDING_COMMENT_BY_NAME("Error finding comment with name: %s"),
    ERROR_FINDING_COMMENT("Error finding comment with id: %s");

    private String message;

    CommentErrorEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
