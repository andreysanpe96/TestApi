package com.service.wolox.api.service.album;

import com.service.wolox.api.enums.album.Action;
import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.album.AlbumPermissionsDTO;

import java.util.List;
import java.util.Optional;

public interface AlbumPermissionServiceInterface {
    AlbumPermissionsDTO create(AlbumPermissionsDTO albumActions) throws ApiWoloxException;

    AlbumPermissionsDTO update(AlbumPermissionsDTO albumActions) throws ApiWoloxException;

    List<AlbumPermissionsDTO> findByAlbumIdAndActions(Long albumId, Action action) throws ApiWoloxException;
}
