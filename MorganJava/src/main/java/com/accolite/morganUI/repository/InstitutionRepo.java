package com.accolite.morganUI.repository;


import com.accolite.morganUI.entity.InstitutionData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstitutionRepo  extends JpaRepository<InstitutionData, Long> {
    Optional<InstitutionData> findByInstName(String instName);
}
