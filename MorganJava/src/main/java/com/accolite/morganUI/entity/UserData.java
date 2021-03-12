package com.accolite.morganUI.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserData {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "user_Sequence")
    @SequenceGenerator(name = "user_Sequence", sequenceName = "USER_SEQ")
    private Long userId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    public UserData(String userName, String email, String password){
        this.name = userName;
        this.email = email;
        this.password = password;
    }
}
