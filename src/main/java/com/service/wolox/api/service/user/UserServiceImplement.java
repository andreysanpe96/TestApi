package com.service.wolox.api.service.user;

import com.service.wolox.api.enums.ErrorMessageEnum;
import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.user.User;
import com.service.wolox.api.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImplement implements UserServiceInterface {

    Logger logger = LoggerFactory.getLogger(UserServiceImplement.class);

    private final RestTemplate apiRestTemplate;

    private final String PATH = "/users";

    public UserServiceImplement(RestTemplate apiRestTemplate) {
        this.apiRestTemplate = apiRestTemplate;
    }

    @Override
    public User findById(Long id) throws ApiWoloxException {
        try {
            Optional<User> user = Optional.ofNullable(apiRestTemplate.getForObject(path, User.class));
            return user.orElseThrow(() -> new ApiWoloxException(Utils.createErrorMessageWithId(ErrorMessageEnum.USER_NOT_FOUND, id),
                                                            HttpStatus.NOT_FOUND, path));
        } catch (HttpClientErrorException.NotFound e) {
            String message = Utils.createErrorMessageWithId(ErrorMessageEnum.USER_NOT_FOUND, id);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.NOT_FOUND, path);
        } catch (HttpClientErrorException e) {
            String message = Utils.createErrorMessageWithId(ErrorMessageEnum.ERROR_FINDING_USER, id);
            logger.error(message);
            throw new ApiWoloxException(message, HttpStatus.SERVICE_UNAVAILABLE, path);
        }
    }

    @Override
    public List<User> findAll() {
        return Arrays.asList(apiRestTemplate.getForObject(path, User[].class));
    }
}
