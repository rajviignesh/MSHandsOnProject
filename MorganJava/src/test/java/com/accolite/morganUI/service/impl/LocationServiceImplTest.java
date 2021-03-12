package com.accolite.morganUI.service.impl;

import com.accolite.morganUI.DTO.InstitutionRequest;
import com.accolite.morganUI.DTO.LocationRequest;
import com.accolite.morganUI.entity.InstitutionData;
import com.accolite.morganUI.entity.LocationData;
import com.accolite.morganUI.repository.InstitutionRepo;
import com.accolite.morganUI.repository.LocationRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LocationServiceImplTest {
    LocationData loco = new LocationData();
    LocationRequest locoreq = new LocationRequest();
    List<LocationData> locoarray = new ArrayList<>();

    @InjectMocks
    @Spy
    LocationServiceImpl locationServiceimpl;

    @Mock
    LocationRepo locationRepo;

    @Mock
    ModelMapper modelMapper;

    @Test
    void createNewLocoTest(){
        Mockito.when(locationRepo.findBylocationName(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(modelMapper.map(Mockito.any(LocationRequest.class),eq(LocationData.class))).thenReturn(loco);
        Mockito.when(locationRepo.save(Mockito.any(LocationData.class))).thenReturn(loco);
        LocationData response = locationServiceimpl.createNewLoco(locoreq);
        Assertions.assertEquals(loco, response);

        Mockito.when(locationRepo.findBylocationName(Mockito.any())).thenReturn(Optional.of(loco));
        LocationData response2 = locationServiceimpl.createNewLoco(locoreq);
        Assertions.assertEquals(null, response2);
    }

    @Test
    void getAllLocations(){
        Mockito.when(locationRepo.findAll()).thenReturn(locoarray);
        List<LocationData> response = locationServiceimpl.getAllLocations();
        Assertions.assertEquals(locoarray, response);
    }
}
