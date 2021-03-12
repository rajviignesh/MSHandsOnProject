package com.accolite.morganUI.repository;

import com.accolite.morganUI.entity.LocationData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepo extends JpaRepository<LocationData, Long> {
    Optional<LocationData> findBylocationName(String locationName);
}
