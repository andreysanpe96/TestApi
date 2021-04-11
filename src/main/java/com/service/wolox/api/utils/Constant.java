package com.service.wolox.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {

    @Value("${user.path.service}")
    public String pathUser;

    @Value("${photo.path.service}")
    public String pathPhoto;

    @Value("${comment.path.service}")
    public String pathComment;

    @Value("${album.path.service}")
    public String pathAlbum;

    @Value("${album.permission.path.service}")
    public String pathAlbumPermission;
}
