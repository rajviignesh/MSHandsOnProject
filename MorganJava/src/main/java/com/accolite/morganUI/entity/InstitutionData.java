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
public class InstitutionData {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "inst_Sequence")
    @SequenceGenerator(name = "inst_Sequence", sequenceName = "INSTITUTION_SEQ")
    private Long InstId;

    @Column
    private String instName;

    @Column
    private String city;

    @Column
    private String pincode;

    public InstitutionData(String instName, String city, String pincode){
        this.instName = instName;
        this.city = city;
        this.pincode = pincode;
    }

}
