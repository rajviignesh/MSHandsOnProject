package com.accolite.morganUI.controller;

import com.accolite.morganUI.DTO.LocationRequest;
import com.accolite.morganUI.entity.LocationData;
import com.accolite.morganUI.service.LocationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LocationControllerTest {
    @InjectMocks
    LocationController locationController;

    @Mock
    LocationService locationService;

    @Test
    void createNewLocoTest(){
        LocationData loco = new LocationData();
        LocationRequest locoreq = new LocationRequest();

        Mockito.when(locationService.createNewLoco(Mockito.any(LocationRequest.class))).thenReturn(loco);
        ResponseEntity<LocationData> response = locationController.createNewloco(locoreq);
        Assertions.assertEquals(loco, response.getBody());

        Mockito.when(locationService.createNewLoco(Mockito.any(LocationRequest.class))).thenReturn(null);
        ResponseEntity<LocationData> response2 = locationController.createNewloco(locoreq);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }

    @Test
    void getAllLocationsTest(){

        LocationData loco = new LocationData();
        List<LocationData> locoarray = new ArrayList<>();
        locoarray.add(loco);

        Mockito.when(locationService.getAllLocations()).thenReturn(locoarray);
        ResponseEntity<List<LocationData>> response = locationController.getAllLocations();
        Assertions.assertEquals(locoarray, response.getBody());

        locoarray.clear();

        Mockito.when(locationService.getAllLocations()).thenReturn(locoarray);
        ResponseEntity<List<LocationData>> response2 = locationController.getAllLocations();
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }
}
