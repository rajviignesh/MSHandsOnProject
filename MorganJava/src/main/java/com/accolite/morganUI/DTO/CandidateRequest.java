package com.accolite.morganUI.DTO;


import lombok.Getter;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
public class CandidateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Long locationId;
    private Set<String> skillList;
    private Long instId;
}
