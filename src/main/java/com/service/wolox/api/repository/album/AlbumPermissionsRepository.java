package com.service.wolox.api.repository.album;

import com.service.wolox.api.entity.album.AlbumPermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumPermissionsRepository  extends JpaRepository<AlbumPermissionsEntity, Long> {

        Optional<AlbumPermissionsEntity> findByAlbumIdAndUserId(Long albumId, Long userId);

        Optional<List<AlbumPermissionsEntity>> findByAlbumIdAndRead(Long albumId, Boolean  read);

        Optional<List<AlbumPermissionsEntity>> findByAlbumIdAndWrite(Long albumId, Boolean  write);

}
