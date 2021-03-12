package com.accolite.morganUI.repository;

import com.accolite.morganUI.entity.CandidateData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepo extends JpaRepository<CandidateData, Long> {
    Optional<CandidateData> findByemail(String email);
}
