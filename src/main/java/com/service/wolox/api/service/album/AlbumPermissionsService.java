package com.service.wolox.api.service.album;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.wolox.api.entity.album.AlbumPermissionsEntity;
import com.service.wolox.api.enums.album.Action;
import com.service.wolox.api.enums.album.AlbumPermissionsErrorEnum;
import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.album.AlbumPermissionsDTO;
import com.service.wolox.api.repository.album.AlbumPermissionsRepository;
import com.service.wolox.api.service.user.UserServiceInterface;
import com.service.wolox.api.utils.Constant;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AlbumPermissionsService implements AlbumPermissionServiceInterface{

    private final AlbumPermissionsRepository albumPermissionsRepository;

    private final UserServiceInterface userService;

    private final AlbumServiceInterface albumService;

    private final Constant constant;

    public AlbumPermissionsService(AlbumPermissionsRepository albumPermissionsRepository, UserServiceInterface userService, AlbumServiceInterface albumService, Constant constant) {
        this.albumPermissionsRepository = albumPermissionsRepository;
        this.userService = userService;
        this.albumService = albumService;
        this.constant = constant;
    }

    @Override
    public AlbumPermissionsDTO create(AlbumPermissionsDTO albumPermissionsDTO) throws ApiWoloxException {
        try{
            // Search user and album, if not exist this methods throws apiWoloxException
            userService.findById(albumPermissionsDTO.getUserId());
            albumService.findById(albumPermissionsDTO.getAlbumId());
            AlbumPermissionsEntity response = albumPermissionsRepository.save(convertToEntity(albumPermissionsDTO));
            return convertToDTO(response);
        }catch (DataIntegrityViolationException e){
            throw new ApiWoloxException(AlbumPermissionsErrorEnum.ALREADY_SAVE, HttpStatus.NOT_IMPLEMENTED, constant.pathAlbumPermission);
        }catch (IllegalArgumentException e){
            throw new ApiWoloxException(AlbumPermissionsErrorEnum.ERROR_SAVE, HttpStatus.NOT_IMPLEMENTED, constant.pathAlbumPermission);
        }
    }

    @Override
    public AlbumPermissionsDTO update(AlbumPermissionsDTO albumPermissionsDTO) throws ApiWoloxException {
            AlbumPermissionsEntity albumActionsDB = findByAlbumIdAndUserId(albumPermissionsDTO.getAlbumId(), albumPermissionsDTO.getUserId())
                    .orElseThrow(() -> new ApiWoloxException(AlbumPermissionsErrorEnum.NOT_FOUND, HttpStatus.NOT_FOUND, constant.pathAlbumPermission));
            albumActionsDB.setRead(albumPermissionsDTO.getRead());
            albumActionsDB.setWrite(albumPermissionsDTO.getWrite());
            return convertToDTO(albumPermissionsRepository.save(albumActionsDB));
    }

    public Optional<AlbumPermissionsEntity> findByAlbumIdAndUserId(Long albumId, Long userId) {
        return albumPermissionsRepository.findByAlbumIdAndUserId(albumId, userId);
    }

    @Override
    public List<AlbumPermissionsDTO> findByAlbumIdAndActions(Long albumId, Action action) throws ApiWoloxException {
        Optional<List<AlbumPermissionsEntity>> response ;
        switch (action) {
            case READ:
                response = albumPermissionsRepository.findByAlbumIdAndRead(albumId, true);
                break;

            case WRITE:
                response = albumPermissionsRepository.findByAlbumIdAndWrite(albumId, true);
                break;
            default:
                throw new ApiWoloxException(AlbumPermissionsErrorEnum.ACTION_NOT_VALID,HttpStatus.NOT_FOUND,constant.pathAlbumPermission);
        }
        return response.orElseThrow(() -> new ApiWoloxException(AlbumPermissionsErrorEnum.NOT_FOUND, HttpStatus.NOT_FOUND, constant.pathAlbumPermission))
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AlbumPermissionsDTO convertToDTO(AlbumPermissionsEntity albumPermissionsentity) {
        return new ObjectMapper().convertValue(albumPermissionsentity, AlbumPermissionsDTO.class);
    }

    private AlbumPermissionsEntity convertToEntity(AlbumPermissionsDTO albumPermissionsDTO) {
        return new ObjectMapper().convertValue(albumPermissionsDTO, AlbumPermissionsEntity.class);
    }
}
