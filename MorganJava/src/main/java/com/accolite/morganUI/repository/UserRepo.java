package com.accolite.morganUI.repository;


import com.accolite.morganUI.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserData, Long> {
    Optional<UserData> findByemail(String email);
}
