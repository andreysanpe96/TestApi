package com.service.wolox.api.enums.album;

import com.service.wolox.api.enums.ErrorEnumInterface;

public enum AlbumPermissionsErrorEnum implements ErrorEnumInterface {

    ALREADY_SAVE("The combination of user and album already exists"),
    NOT_FOUND("AlbumPermission not found"),
    ACTION_NOT_VALID("Action filter is not valid"),
    ERROR_SAVE("Error saving albumPermissions");

    private String message;

    AlbumPermissionsErrorEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
