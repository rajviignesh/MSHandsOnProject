package com.accolite.morganUI.controller;

import com.accolite.morganUI.DTO.UserRequest;
import com.accolite.morganUI.entity.UserData;
import com.accolite.morganUI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserData> addUser(@RequestBody UserRequest userreq){
        UserData response = userService.addUser(userreq);
        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserData> signin(@RequestBody UserRequest userreq ){
        UserData response = userService.signIn(userreq);
        if(response == null){
            return ResponseEntity.notFound().build();
        }
        response.setPassword("");
        return ResponseEntity.ok(response);
    }
}
