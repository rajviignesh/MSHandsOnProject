package com.accolite.morganUI.service.impl;

import com.accolite.morganUI.DTO.LocationRequest;
import com.accolite.morganUI.entity.CandidateData;
import com.accolite.morganUI.entity.LocationData;
import com.accolite.morganUI.repository.LocationRepo;
import com.accolite.morganUI.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepo locationRepo;

    @Autowired
    ModelMapper modelMapper;

    public LocationData createNewLoco(LocationRequest locoreq) {
        if (!(locationRepo.findBylocationName(locoreq.getLocationName()).isPresent())) {
            LocationData loco = modelMapper.map(locoreq, LocationData.class);
            System.out.println(loco);
            LocationData savedLoco = locationRepo.save(loco);
            return savedLoco;
        }
        return null;
    }

    @Override
    public List<LocationData> getAllLocations(){
        List<LocationData> locationDataList = locationRepo.findAll();
        return locationDataList;
    }
}
