package com.service.wolox.api.service.comment;

import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.comment.Comment;

import java.util.List;

public interface CommentServiceInterface {

    public List<Comment> findAll() throws ApiWoloxException;

    public Comment findById(Long id) throws ApiWoloxException;

    public List<Comment> findByUserOrName(Long userId, String name) throws ApiWoloxException;
}
