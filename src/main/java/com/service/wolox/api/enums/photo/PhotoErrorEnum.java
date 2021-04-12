package com.service.wolox.api.enums.photo;

import com.service.wolox.api.enums.ErrorEnumInterface;

public enum PhotoErrorEnum implements ErrorEnumInterface {

    PHOTO_NOT_FOUND("Photo with id: %s was not found"),
    NOT_PHOTOS("Not photo found"),
    PHOTO_BY_USER_NOT_FOUND("Photos from userId: %s was not found"),
    PHOTO_BY_ALBUM_NOT_FOUND("Photos from albumId: %s was not found"),
    NOT_PHOTOS_BY_USER("Not photo found from userId: %s "),
    ERROR_FINDING_ALL("Error finding all photos"),
    ERROR_FINDING_PHOTO("Error finding photo with id: %s");

    private String message;

    PhotoErrorEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
