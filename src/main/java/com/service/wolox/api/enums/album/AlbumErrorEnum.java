package com.service.wolox.api.enums.album;

import com.service.wolox.api.enums.ErrorEnumInterface;

public enum AlbumErrorEnum implements ErrorEnumInterface {

    ALBUM_NOT_FOUND("Album with id: %s was not found"),
    ALBUM_BY_USER_NOT_FOUND("Album with userId: %s was not found"),
    ERROR_FINDING_ALBUM_BY_USER("Error finding Album with userId: %s"),
    NOT_ALBUMS("Not Album found"),
    ERROR_FINDING_ALL("Error finding all albums"),
    ERROR_FINDING_ALBUM("Error finding album with id: %s");

    private String message;

    AlbumErrorEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
