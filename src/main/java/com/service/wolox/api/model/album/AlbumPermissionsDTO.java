package com.service.wolox.api.model.album;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
@Builder
public class AlbumPermissionsDTO implements Serializable {

    private Long userId;

    private Long albumId;

    private Boolean read;

    private Boolean write;
}
