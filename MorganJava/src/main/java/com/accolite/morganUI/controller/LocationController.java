package com.accolite.morganUI.controller;

import com.accolite.morganUI.DTO.LocationRequest;
import com.accolite.morganUI.entity.LocationData;
import com.accolite.morganUI.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @PostMapping("/create")
    public ResponseEntity<LocationData> createNewloco(@RequestBody LocationRequest locoreq) {
        LocationData response = locationService.createNewLoco(locoreq);
        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/alllocations")
    public ResponseEntity<List<LocationData>> getAllLocations(){
        List<LocationData> response = locationService.getAllLocations();
        if(!response.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);

    }
}
