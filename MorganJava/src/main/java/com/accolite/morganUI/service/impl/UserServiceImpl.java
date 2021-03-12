package com.accolite.morganUI.service.impl;

import com.accolite.morganUI.DTO.UserRequest;
import com.accolite.morganUI.entity.UserData;
import com.accolite.morganUI.repository.UserRepo;
import com.accolite.morganUI.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserData addUser(UserRequest userreq){
        if (!(userRepo.findByemail(userreq.getEmail()).isPresent())) {
            String plainPassword = userreq.getPassword();
            log.info(plainPassword);
            String encryptedPassword = bCryptPasswordEncoder.encode(plainPassword);
            UserData user = new UserData(userreq.getName(), userreq.getEmail(), encryptedPassword);
            UserData savedUser = userRepo.save(user);
            return savedUser;
        }
        return null;
    }

    @Override
    public UserData signIn(UserRequest userreq){
        if ((userRepo.findByemail(userreq.getEmail()).isPresent())) {
            UserData user = userRepo.findByemail(userreq.getEmail()).get();
            if(bCryptPasswordEncoder.matches(userreq.getPassword(), user.getPassword())){
                return user;
            }
            return null;
        }
        return null;
    }

}
