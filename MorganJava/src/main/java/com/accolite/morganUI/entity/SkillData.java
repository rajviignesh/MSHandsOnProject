package com.accolite.morganUI.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class SkillData {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "skill_Sequence")
    @SequenceGenerator(name = "skill_Sequence", sequenceName = "SKILL_SEQ")
    private Long skillId;

    @Column
    private Long candId;

    @Column
    private String skillName;

    public SkillData(Long candId, String skillName){
        this.candId = candId;
        this.skillName = skillName;
    }
}
