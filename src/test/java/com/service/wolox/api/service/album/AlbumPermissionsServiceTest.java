package com.service.wolox.api.service.album;

import com.service.wolox.api.entity.album.AlbumPermissionsEntity;
import com.service.wolox.api.enums.album.Action;
import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.album.Album;
import com.service.wolox.api.model.album.AlbumPermissionsDTO;
import com.service.wolox.api.model.user.User;
import com.service.wolox.api.repository.album.AlbumPermissionsRepository;
import com.service.wolox.api.service.user.UserServiceInterface;
import com.service.wolox.api.utils.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class AlbumPermissionsServiceTest {

    @InjectMocks
    private AlbumPermissionsService service;

    private final AlbumPermissionsRepository albumPermissionsRepository = mock(AlbumPermissionsRepository.class);

    private final UserServiceInterface userService = mock(UserServiceInterface.class);

    private final AlbumServiceInterface albumService = mock(AlbumServiceInterface.class);

    private final Constant constant = mock(Constant.class);

    @BeforeEach
    public void setUp() {
        service = new AlbumPermissionsService(albumPermissionsRepository,userService,albumService , constant);
        constant.pathUser ="/albumPermissions";
    }

    @Test
    void testCreateWhenReturnSuccessResponse() throws ApiWoloxException {
        Long userId = 2L;
        Long albumId = 2L;
        AlbumPermissionsDTO albumPermissionsDTO = AlbumPermissionsDTO.builder().userId(userId).albumId(albumId).build();
        AlbumPermissionsEntity entity = new AlbumPermissionsEntity();
        entity.setUserId(userId);
        entity.setAlbumId(albumId);

        when(userService.findById(userId)).thenReturn(User.builder().build());
        when(albumService.findById(albumId)).thenReturn(Album.builder().build());
        when(albumPermissionsRepository.save(any())).thenReturn(entity);

        AlbumPermissionsDTO response = service.create(albumPermissionsDTO);

        assertEquals(response.getUserId(), userId);
        assertEquals(response.getAlbumId(), albumId);
    }

    @Test
    void testCreateWhenReturnDataIntegrityViolationExceptionAndThrowApiWoloxException() throws ApiWoloxException {
        Long userId = 2L;
        Long albumId = 2L;
        AlbumPermissionsDTO albumPermissionsDTO = AlbumPermissionsDTO.builder().userId(userId).albumId(albumId).build();
        when(userService.findById(userId)).thenReturn(User.builder().build());
        when(albumService.findById(albumId)).thenReturn(Album.builder().build());
        when(albumPermissionsRepository.save(any())).thenThrow(new DataIntegrityViolationException(null));

        try {
            service.create(albumPermissionsDTO);
        }catch (ApiWoloxException e){
            assertEquals(HttpStatus.NOT_IMPLEMENTED, e.getStatus());
        }
    }

    @Test
    void testCreateWhenReturnIllegalArgumentExceptionAndThrowApiWoloxException() throws ApiWoloxException {
        Long userId = 2L;
        Long albumId = 2L;
        AlbumPermissionsDTO albumPermissionsDTO = AlbumPermissionsDTO.builder().userId(userId).albumId(albumId).build();
        when(userService.findById(userId)).thenReturn(User.builder().build());
        when(albumService.findById(albumId)).thenReturn(Album.builder().build());
        when(albumPermissionsRepository.save(any())).thenThrow(new IllegalArgumentException());

        try {
            service.create(albumPermissionsDTO);
        }catch (ApiWoloxException e){
            assertEquals(HttpStatus.NOT_IMPLEMENTED, e.getStatus());
        }
    }

    @Test
    void testUpdateWhenReturnSuccessResponse() throws ApiWoloxException {
        Long userId = 2L;
        Long albumId = 2L;
        AlbumPermissionsDTO albumPermissionsDTO = AlbumPermissionsDTO.builder().userId(userId).albumId(albumId).build();

        AlbumPermissionsEntity entity = new AlbumPermissionsEntity();
        entity.setUserId(userId);
        entity.setAlbumId(albumId);

        when(albumPermissionsRepository.findByAlbumIdAndUserId(albumId,userId)).thenReturn(Optional.of(entity));
        when(albumPermissionsRepository.save(any())).thenReturn(entity);
        AlbumPermissionsDTO response = service.update(albumPermissionsDTO);

        assertEquals(response.getUserId(), userId);
        assertEquals(response.getAlbumId(), albumId);
    }

    @Test
    void testFindByAlbumIdAndActionsWhenActionIsReadAndReturnSuccessResponse() throws ApiWoloxException {
        Long albumId = 2L;
        Long userId = 2L;
        AlbumPermissionsEntity entity = new AlbumPermissionsEntity();
        entity.setUserId(userId);
        entity.setAlbumId(albumId);
        when(albumPermissionsRepository.findByAlbumIdAndRead(albumId,true)).thenReturn(Optional.of(Arrays.asList(entity)));

        List<AlbumPermissionsDTO> response = service.findByAlbumIdAndActions(albumId, Action.READ);

        assertEquals(response.size(), 1);
        assertEquals(response.get(0).getAlbumId(), albumId);
    }

    @Test
    void testFindByAlbumIdAndActionsWhenActionIsWriteAndReturnSuccessResponse() throws ApiWoloxException {
        Long albumId = 2L;
        Long userId = 2L;
        AlbumPermissionsEntity entity = new AlbumPermissionsEntity();
        entity.setUserId(userId);
        entity.setAlbumId(albumId);
        when(albumPermissionsRepository.findByAlbumIdAndWrite(albumId,true)).thenReturn(Optional.of(Arrays.asList(entity)));

        List<AlbumPermissionsDTO> response = service.findByAlbumIdAndActions(albumId, Action.WRITE);

        assertEquals(response.size(), 1);
        assertEquals(response.get(0).getAlbumId(), albumId);
    }

}