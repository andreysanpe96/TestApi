package com.service.wolox.api.service.comment;

import com.service.wolox.api.enums.album.AlbumErrorEnum;
import com.service.wolox.api.enums.comment.CommentErrorEnum;
import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.album.Album;
import com.service.wolox.api.model.comment.Comment;
import com.service.wolox.api.service.album.AlbumServiceImplement;
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
public class CommentServiceImplement implements CommentServiceInterface{

    Logger logger = LoggerFactory.getLogger(CommentServiceImplement.class);

    private final RestTemplate apiRestTemplate;
    private final Constant constant;

    public CommentServiceImplement(RestTemplate apiRestTemplate, Constant constant) {
        this.apiRestTemplate = apiRestTemplate;
        this.constant = constant;
    }

    @Override
    public Comment findById(Long albumId) throws ApiWoloxException {
        try {
            Optional<Comment> comment = Optional.ofNullable(apiRestTemplate.getForObject(constant.pathComment + "/" + albumId, Comment.class));
            return comment.orElseThrow(() ->
                    new ApiWoloxException(Utils.createErrorMessageWithId(CommentErrorEnum.COMMENT_NOT_FOUND, albumId),
                            HttpStatus.NOT_FOUND, constant.pathComment)
            );
        } catch (HttpClientErrorException.NotFound e) {
            String message = Utils.createErrorMessageWithId(CommentErrorEnum.COMMENT_NOT_FOUND, albumId);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.NOT_FOUND, constant.pathComment);
        } catch (HttpClientErrorException e) {
            String message = Utils.createErrorMessageWithId(CommentErrorEnum.ERROR_FINDING_COMMENT, albumId);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.SERVICE_UNAVAILABLE, constant.pathComment);
        }
    }

    @Override
    public List<Comment> findAll() throws ApiWoloxException {
        try {
            Comment[] listComment = apiRestTemplate.getForObject(constant.pathComment, Comment[].class);
            if (ArrayUtils.isEmpty(listComment)) {
                throw new ApiWoloxException(CommentErrorEnum.NOT_COMMENT,HttpStatus.NOT_FOUND, constant.pathComment);
            } else {
                return Arrays.asList(listComment);
            }
        } catch (HttpClientErrorException.NotFound e) {
            String message = CommentErrorEnum.NOT_COMMENT.getMessage();
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.NOT_FOUND, constant.pathComment);
        } catch (HttpClientErrorException e) {
            String message = CommentErrorEnum.ERROR_FINDING_ALL.getMessage();
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.SERVICE_UNAVAILABLE, constant.pathComment);
        }
    }

    @Override
    public List<Comment> findByUserOrName(Long userId, String name) {
        return null;
    }
}
