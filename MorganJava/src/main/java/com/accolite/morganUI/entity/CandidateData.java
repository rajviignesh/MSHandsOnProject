package com.accolite.morganUI.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CandidateData {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "cand_Sequence")
    @SequenceGenerator(name = "cand_Sequence", sequenceName = "CANDIDATE_SEQ")
    private Long candId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private Boolean status;

    @OneToMany(mappedBy = "candId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<SkillData> skillSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loco_id", nullable = false)
    private LocationData locoData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inst_id", nullable = false)
    private InstitutionData instData;

    public CandidateData(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
