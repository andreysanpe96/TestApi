package com.service.wolox.api.service.user;

import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.user.User;

import java.util.List;

public interface UserServiceInterface {

    User findById(Long id) throws ApiWoloxException;

    List<User> findAll() throws ApiWoloxException;
}
