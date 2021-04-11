package com.service.wolox.api.service.album;

import com.service.wolox.api.enums.album.AlbumErrorEnum;
import com.service.wolox.api.enums.user.UserErrorEnum;
import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.album.Album;
import com.service.wolox.api.utils.Constant;
import com.service.wolox.api.utils.Utils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class AlbumServiceImplement implements AlbumServiceInterface {

    Logger logger = LoggerFactory.getLogger(AlbumServiceImplement.class);

    private final RestTemplate apiRestTemplate;
    private final Constant constant;

    public AlbumServiceImplement(RestTemplate apiRestTemplate, Constant constant) {
        this.apiRestTemplate = apiRestTemplate;
        this.constant = constant;
    }

    @Override
    public Album findById(Long albumId) throws ApiWoloxException {
        try {
            Optional<Album> album = Optional.ofNullable(apiRestTemplate.getForObject(constant.pathAlbum + "/" + albumId, Album.class));
            return album.orElseThrow(() ->
                    new ApiWoloxException(Utils.createErrorMessageWithId(AlbumErrorEnum.ALBUM_NOT_FOUND, albumId),
                            HttpStatus.NOT_FOUND, constant.pathAlbum)
            );
        } catch (HttpClientErrorException.NotFound e) {
            String message = Utils.createErrorMessageWithId(AlbumErrorEnum.ALBUM_NOT_FOUND, albumId);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.NOT_FOUND, constant.pathAlbum);
        } catch (HttpClientErrorException e) {
            String message = Utils.createErrorMessageWithId(AlbumErrorEnum.ERROR_FINDING_ALBUM, albumId);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.SERVICE_UNAVAILABLE, constant.pathAlbum);
        }
    }

    @Override
    public List<Album> findAll() throws ApiWoloxException {
        try {
            Album[] listAlbum = apiRestTemplate.getForObject(constant.pathAlbum, Album[].class);
            if (ArrayUtils.isEmpty(listAlbum)) {
                throw new ApiWoloxException(AlbumErrorEnum.NOT_ALBUMS, HttpStatus.NOT_FOUND, constant.pathAlbum);
            } else {
                return Arrays.asList(listAlbum);
            }
        } catch (HttpClientErrorException.NotFound e) {
            String message = AlbumErrorEnum.NOT_ALBUMS.getMessage();
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.NOT_FOUND, constant.pathAlbum);
        } catch (HttpClientErrorException e) {
            String message = AlbumErrorEnum.ERROR_FINDING_ALL.getMessage();
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.SERVICE_UNAVAILABLE, constant.pathAlbum);
        }
    }

    @Override
    public List<Album> findByUserId(Long userId) throws ApiWoloxException {
        try {
            Album[] listAlbum = apiRestTemplate.getForObject(constant.pathAlbum + "?userId=" + userId, Album[].class);
            if (ArrayUtils.isEmpty(listAlbum)) {
                throw new ApiWoloxException(Utils.createErrorMessageWithId(AlbumErrorEnum.ALBUM_BY_USER_NOT_FOUND, userId),
                        HttpStatus.NOT_FOUND, constant.pathAlbum);
            } else {
                return Arrays.asList(listAlbum);
            }
        } catch (HttpClientErrorException.NotFound e) {
            String message = Utils.createErrorMessageWithId(AlbumErrorEnum.ALBUM_BY_USER_NOT_FOUND, userId);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.NOT_FOUND, constant.pathAlbum);
        } catch (HttpClientErrorException e) {
            String message = Utils.createErrorMessageWithId(AlbumErrorEnum.ERROR_FINDING_ALBUM_BY_USER, userId);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.SERVICE_UNAVAILABLE, constant.pathAlbum);
        }
    }
}
