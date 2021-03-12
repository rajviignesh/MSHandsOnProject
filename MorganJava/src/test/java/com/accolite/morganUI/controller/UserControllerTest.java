package com.accolite.morganUI.controller;


import com.accolite.morganUI.DTO.CandidateRequest;
import com.accolite.morganUI.DTO.UserRequest;
import com.accolite.morganUI.entity.CandidateData;
import com.accolite.morganUI.entity.UserData;
import com.accolite.morganUI.service.LocationService;
import com.accolite.morganUI.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Test
    void addUserTest(){
        UserData user = new UserData();
        UserRequest userreq = new UserRequest();

        Mockito.when(userService.addUser(Mockito.any(UserRequest.class))).thenReturn(user);
        ResponseEntity<UserData> response = userController.addUser(userreq);
        Assertions.assertEquals(user, response.getBody());

        Mockito.when(userService.addUser(Mockito.any(UserRequest.class))).thenReturn(null);
        ResponseEntity<UserData> response2 = userController.addUser(userreq);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());

    }

    @Test
    void signinTest(){
        UserData user = new UserData();
        UserRequest userreq = new UserRequest();

        Mockito.when(userService.signIn(Mockito.any(UserRequest.class))).thenReturn(user);
        ResponseEntity<UserData> response = userController.signin(userreq);
        Assertions.assertEquals(user, response.getBody());

        Mockito.when(userService.signIn(Mockito.any(UserRequest.class))).thenReturn(null);
        ResponseEntity<UserData> response2 = userController.signin(userreq);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }
}
