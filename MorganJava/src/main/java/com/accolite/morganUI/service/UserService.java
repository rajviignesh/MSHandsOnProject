package com.accolite.morganUI.service;

import com.accolite.morganUI.DTO.UserRequest;
import com.accolite.morganUI.entity.UserData;

public interface UserService {
    public UserData addUser(UserRequest userreq);
    public UserData signIn(UserRequest userreq);
}
