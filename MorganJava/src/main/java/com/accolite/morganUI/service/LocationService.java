package com.accolite.morganUI.service;


import com.accolite.morganUI.DTO.LocationRequest;
import com.accolite.morganUI.entity.LocationData;

import java.util.List;

public interface LocationService {
    public LocationData createNewLoco(LocationRequest locoreq);
    public List<LocationData> getAllLocations();
}
