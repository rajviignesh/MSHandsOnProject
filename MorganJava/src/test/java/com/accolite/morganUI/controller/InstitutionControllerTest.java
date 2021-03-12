package com.accolite.morganUI.controller;



import com.accolite.morganUI.DTO.InstitutionRequest;
import com.accolite.morganUI.entity.InstitutionData;
import com.accolite.morganUI.service.InstitutionService;
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
public class InstitutionControllerTest {
    @InjectMocks
    InstitutionController institutionController;

    @Mock
    InstitutionService institutionService;

    @Test
    void createNewInstTest(){
        InstitutionData inst = new InstitutionData();
        InstitutionRequest instreq = new InstitutionRequest();

        Mockito.when(institutionService.createNewInst(Mockito.any(InstitutionRequest.class))).thenReturn(inst);
        ResponseEntity<InstitutionData> response = institutionController.createNewInst(instreq);
        Assertions.assertEquals(inst, response.getBody());

        Mockito.when(institutionController.createNewInst(Mockito.any(InstitutionRequest.class))).thenReturn(null);
        ResponseEntity<InstitutionData> response2 = institutionController.createNewInst(instreq);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }

    @Test
    void getAllInstitutionsTest(){

        InstitutionData inst = new InstitutionData();
        List<InstitutionData> instarray = new ArrayList<>();
        instarray.add(inst);

        Mockito.when(institutionService.getAllInsts()).thenReturn(instarray);
        ResponseEntity<List<InstitutionData>> response = institutionController.getAllInstitutions();
        Assertions.assertEquals(instarray, response.getBody());

        instarray.clear();

        Mockito.when(institutionService.getAllInsts()).thenReturn(instarray);
        ResponseEntity<List<InstitutionData>> response2 = institutionController.getAllInstitutions();
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }
}
