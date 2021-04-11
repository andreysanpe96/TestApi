package com.service.wolox.api.service.photo;

import com.service.wolox.api.enums.photo.PhotoErrorEnum;
import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.album.Album;
import com.service.wolox.api.model.photo.Photo;
import com.service.wolox.api.service.album.AlbumServiceInterface;
import com.service.wolox.api.utils.Constant;
import com.service.wolox.api.utils.Utils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PhotoServiceImplement implements PhotoServiceInterface {

    Logger logger = LoggerFactory.getLogger(PhotoServiceImplement.class);

    private final RestTemplate apiRestTemplate;

    private final Constant constant;

    private final AlbumServiceInterface albumService;

    public PhotoServiceImplement(RestTemplate apiRestTemplate, Constant constant, AlbumServiceInterface albumService) {
        this.apiRestTemplate = apiRestTemplate;
        this.constant = constant;
        this.albumService = albumService;
    }

    @Override
    public Photo findById(Long photoId) throws ApiWoloxException {
        try {
            Optional<Photo> photo = Optional.ofNullable(apiRestTemplate.getForObject(constant.pathPhoto + "/" + photoId, Photo.class));
            return photo.orElseThrow(() ->
                    new ApiWoloxException(Utils.createErrorMessageWithId(PhotoErrorEnum.PHOTO_NOT_FOUND, photoId),
                            HttpStatus.NOT_FOUND, constant.pathPhoto)
            );
        } catch (HttpClientErrorException.NotFound e) {
            String message = Utils.createErrorMessageWithId(PhotoErrorEnum.PHOTO_NOT_FOUND, photoId);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.NOT_FOUND, constant.pathPhoto);
        } catch (HttpClientErrorException e) {
            String message = Utils.createErrorMessageWithId(PhotoErrorEnum.ERROR_FINDING_PHOTO, photoId);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.SERVICE_UNAVAILABLE, constant.pathPhoto);
        }
    }

    @Override
    public List<Photo> findAll() throws ApiWoloxException {
        try {
            Photo[] listPhoto = apiRestTemplate.getForObject(constant.pathPhoto, Photo[].class);
            if(ArrayUtils.isEmpty(listPhoto)){
                throw new ApiWoloxException(PhotoErrorEnum.NOT_PHOTOS, HttpStatus.NOT_FOUND, constant.pathPhoto);
            }else{
                return Arrays.asList(listPhoto);
            }
        } catch (HttpClientErrorException.NotFound e) {
            String message = PhotoErrorEnum.NOT_PHOTOS.getMessage();
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.NOT_FOUND, constant.pathPhoto);
        } catch (HttpClientErrorException e) {
            String message = PhotoErrorEnum.ERROR_FINDING_ALL.getMessage();
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.SERVICE_UNAVAILABLE, constant.pathPhoto);
        }
    }

    @Override
    public List<Photo> findByUserId(Long userId) throws ApiWoloxException {
        try {
            List<Album> listAlbum = albumService.findByUserId(userId);
            List<Photo> listPhoto = listAlbum.stream().flatMap(
                    album -> findByAlbumId(album.getId()).stream()).collect(Collectors.toList());
            if(CollectionUtils.isEmpty(listPhoto)){
                throw new ApiWoloxException(PhotoErrorEnum.NOT_PHOTOS, HttpStatus.NOT_FOUND, constant.pathPhoto);
            }else {
                return listPhoto;
            }
        } catch (HttpClientErrorException.NotFound e) {
            String message = Utils.createErrorMessageWithId(PhotoErrorEnum.PHOTO_BY_USER_NOT_FOUND, userId);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.NOT_FOUND, constant.pathPhoto);
        } catch (HttpClientErrorException e) {
            String message = Utils.createErrorMessageWithId(PhotoErrorEnum.NOT_PHOTOS_BY_USER, userId);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.SERVICE_UNAVAILABLE, constant.pathPhoto);
        }
    }

    @Override
    public List<Photo> findByAlbumId(Long albumId) {
        try {
            Photo[] photos = apiRestTemplate.getForObject(constant.pathPhoto + "?albumId=" + albumId, Photo[].class);
            return ArrayUtils.isEmpty(photos) ? Arrays.asList(photos) : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            String message = Utils.createErrorMessageWithId(PhotoErrorEnum.PHOTO_BY_ALBUM_NOT_FOUND, albumId);
            logger.error(message);
            return new ArrayList<>();
        }
    }
}
