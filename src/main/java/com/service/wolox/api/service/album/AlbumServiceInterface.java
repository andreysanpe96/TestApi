package com.service.wolox.api.service.album;

import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.album.Album;

import java.util.List;

public interface AlbumServiceInterface {

    List<Album> findAll() throws ApiWoloxException;

    Album findById(Long id) throws ApiWoloxException;

    List<Album> findByUserId(Long userId) throws ApiWoloxException;
}
