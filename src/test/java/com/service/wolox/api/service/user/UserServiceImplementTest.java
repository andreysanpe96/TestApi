package com.service.wolox.api.service.user;

import com.service.wolox.api.exception.ApiWoloxException;
import com.service.wolox.api.model.user.User;
import com.service.wolox.api.utils.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplementTest {

    @InjectMocks
    private UserServiceImplement service;

    private final RestTemplate apiRestTemplate = mock(RestTemplate.class);

    private final Constant constant = mock(Constant.class);

    @BeforeEach
    public void setUp() {
        service = new UserServiceImplement(apiRestTemplate, constant);
        constant.pathUser ="/users";
    }

    @Test
    void testFindByIdWhenReturnSuccessResponse() throws ApiWoloxException {
        String email = "correp@wolox.com";
        Long userId = 2L;
        User user = User.builder().email(email).build();

        when(apiRestTemplate.getForObject(anyString(), any())).thenReturn(user);

        User response = service.findById(userId);

        assertEquals(response.getEmail(), email);
    }

    @Test
    void testFindByIdWhenResponseIsNullAndThrowApiWoloxException() {
        when(apiRestTemplate.getForObject(anyString(), any())).thenReturn(null);
        try {
            User response = service.findById(2L);
        } catch (ApiWoloxException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
        }
    }

    @Test
    void testFindByIdWhenResponseIsNotFoundAndThrowApiWoloxException() {
        when(apiRestTemplate.getForObject(anyString(), any()))
                .thenThrow(HttpClientErrorException.create(HttpStatus.NOT_FOUND, "not found", null, null, null));
        try {
            service.findById(2L);
        } catch (ApiWoloxException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
        }
    }

    @Test
    void testFindByIdWhenResponseIsErrorServerAndThrowApiWoloxException() {
        when(apiRestTemplate.getForObject(anyString(), any())).thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        try {
            service.findById(2L);
        } catch (ApiWoloxException e) {
            assertEquals(HttpStatus.SERVICE_UNAVAILABLE, e.getStatus());
        }
    }

    @Test
    void testFindAllWhenReturnSuccessResponse() throws ApiWoloxException {
        String email = "correp@wolox.com";
        User user = User.builder().email(email).build();
        User[] userVector = new User[1];
        userVector[0] = user;
        when(apiRestTemplate.getForObject(anyString(), any())).thenReturn(userVector);

        List<User> response = service.findAll();

        assertEquals(response.size(),1);
        assertEquals(response.get(0).getEmail(),email);
    }

    @Test
    void testFindAllWhenVectorIsnullAndThrowApiWoloxException() throws ApiWoloxException {

        when(apiRestTemplate.getForObject(anyString(), any())).thenReturn(null);

        try{
            List<User> response = service.findAll();
        }catch (ApiWoloxException e){
            assertEquals( HttpStatus.NOT_FOUND,e.getStatus());
        }

    }

    @Test
    void testFindAllWhenResponseIsNotFoundAndThrowApiWoloxException() throws ApiWoloxException {

        when(apiRestTemplate.getForObject(anyString(), any()))
                .thenThrow(HttpClientErrorException.create(HttpStatus.NOT_FOUND, "not found", null, null, null));

        try{
            List<User> response = service.findAll();
        }catch (ApiWoloxException e){
            assertEquals( HttpStatus.NOT_FOUND,e.getStatus());
        }

    }

    @Test
    void testFindAllWhenResponseIsServerErrorAndThrowApiWoloxException() throws ApiWoloxException {

        when(apiRestTemplate.getForObject(anyString(), any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        try{
            List<User> response = service.findAll();
        }catch (ApiWoloxException e){
            assertEquals( HttpStatus.SERVICE_UNAVAILABLE,e.getStatus());
        }

    }
}