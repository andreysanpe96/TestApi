package com.service.wolox.api.service.photo;

import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.photo.Photo;

import java.util.List;

public interface PhotoServiceInterface {

    List<Photo> findAll() throws ApiWoloxException;

    Photo findById(Long id) throws ApiWoloxException;

    List<Photo> findByUserId(Long userId) throws ApiWoloxException;

    List<Photo> findByAlbumId(Long albumId) throws ApiWoloxException;

}
