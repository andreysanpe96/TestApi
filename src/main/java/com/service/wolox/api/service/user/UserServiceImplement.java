package com.service.wolox.api.service.user;

import com.service.wolox.api.enums.user.UserErrorEnum;
import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.user.User;
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
public class UserServiceImplement implements UserServiceInterface {

    Logger logger = LoggerFactory.getLogger(UserServiceImplement.class);

    private final RestTemplate apiRestTemplate;

    private final Constant constant;

    public UserServiceImplement(RestTemplate apiRestTemplate, Constant constant) {
        this.apiRestTemplate = apiRestTemplate;
        this.constant = constant;
    }

    @Override
    public User findById(Long id) throws ApiWoloxException {
        try {
            Optional<User> user = Optional.ofNullable(apiRestTemplate.getForObject(constant.pathUser + "/" + id, User.class));
            return user.orElseThrow(() ->
                    new ApiWoloxException(Utils.createErrorMessageWithValue(UserErrorEnum.USER_NOT_FOUND, id),
                            HttpStatus.NOT_FOUND, constant.pathUser)
            );
        } catch (HttpClientErrorException.NotFound e) {
            String message = Utils.createErrorMessageWithValue(UserErrorEnum.USER_NOT_FOUND, id);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.NOT_FOUND, constant.pathUser);
        } catch (HttpClientErrorException e) {
            String message = Utils.createErrorMessageWithValue(UserErrorEnum.ERROR_FINDING_USER, id);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.SERVICE_UNAVAILABLE, constant.pathUser);
        }
    }

    @Override
    public List<User> findAll() throws ApiWoloxException {
        try {
            User[] listUsers = apiRestTemplate.getForObject(constant.pathUser, User[].class);
            if(ArrayUtils.isEmpty(listUsers)){
                throw new ApiWoloxException(UserErrorEnum.NOT_USERS, HttpStatus.NOT_FOUND, constant.pathUser);
            }else{
                return Arrays.asList(listUsers);
            }
        } catch (HttpClientErrorException.NotFound e) {
            String message = UserErrorEnum.NOT_USERS.getMessage();
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.NOT_FOUND, constant.pathUser);
        } catch (HttpClientErrorException e) {
            String message = UserErrorEnum.ERROR_FINDING_ALL.getMessage();
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.SERVICE_UNAVAILABLE, constant.pathUser);
        }
    }
}
