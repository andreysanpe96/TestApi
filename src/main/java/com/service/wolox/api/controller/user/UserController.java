package com.service.wolox.api.controller.user;

import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.user.User;
import com.service.wolox.api.service.user.UserServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceInterface userService;

    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Operation(summary = "Method to search user by id")
    @GetMapping("/{userId}")
    public ResponseEntity<User> user(@PathVariable Long userId) throws ApiWoloxException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
    }

    @Operation(summary = "Method to search all users")
    @GetMapping
    public Object users() throws ApiWoloxException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

}
