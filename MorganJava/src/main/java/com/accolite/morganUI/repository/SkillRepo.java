package com.accolite.morganUI.repository;


import com.accolite.morganUI.entity.SkillData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SkillRepo extends JpaRepository<SkillData, Long> {
    public Set<SkillData> findBycandId(Long candId);
}
