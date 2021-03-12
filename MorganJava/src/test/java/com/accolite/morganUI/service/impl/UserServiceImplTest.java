package com.accolite.morganUI.service.impl;

import com.accolite.morganUI.DTO.CandidateRequest;
import com.accolite.morganUI.DTO.UserRequest;
import com.accolite.morganUI.entity.CandidateData;
import com.accolite.morganUI.entity.UserData;
import com.accolite.morganUI.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceImplTest {

    UserData user = new UserData();
    UserRequest userreq = new UserRequest();

    @InjectMocks
    @Spy
    UserServiceImpl userServiceimpl;

    @Mock
    UserRepo userRepo;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void addUserTest(){
        Mockito.when(userRepo.findByemail(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(userRepo.save(Mockito.any(UserData.class))).thenReturn(user);
        Mockito.when(bCryptPasswordEncoder.encode(Mockito.any())).thenReturn(user.getPassword());
        UserData response = userServiceimpl.addUser(userreq);
        Assertions.assertEquals(user, response);

        Mockito.when(userRepo.findByemail(Mockito.any())).thenReturn(Optional.of(user));
        UserData response2 = userServiceimpl.addUser(userreq);
        Assertions.assertEquals(null, response2);
    }

    @Test
    void signInTest(){
        Mockito.when(userRepo.findByemail(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(userRepo.findByemail(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(bCryptPasswordEncoder.matches(Mockito.any(),Mockito.any())).thenReturn(true);
        UserData response = userServiceimpl.signIn(userreq);
        Assertions.assertEquals(user, response);

        Mockito.when(userRepo.findByemail(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(userRepo.findByemail(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(bCryptPasswordEncoder.matches(Mockito.any(),Mockito.any())).thenReturn(false);
        UserData response2 = userServiceimpl.signIn(userreq);
        Assertions.assertEquals(null, response2);

        Mockito.when(userRepo.findByemail(Mockito.any())).thenReturn(Optional.empty());
        UserData response3 = userServiceimpl.signIn(userreq);
        Assertions.assertEquals(null, response3);
    }
}
