package com.accolite.morganUI.controller;

import com.accolite.morganUI.DTO.CandidateRequest;
import com.accolite.morganUI.entity.CandidateData;
import com.accolite.morganUI.service.CandidateService;
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
class CandidateControllerTest {
    @InjectMocks
    CandidateController candidateController;

    @Mock
    CandidateService candidateService;

    @Test
    void createNewCandTest(){

        CandidateData cand = new CandidateData();
        CandidateRequest candreq = new CandidateRequest();

        Mockito.when(candidateService.createNewCand(Mockito.any(CandidateRequest.class))).thenReturn(cand);
        ResponseEntity<CandidateData> response = candidateController.createNewCand(candreq);
        Assertions.assertEquals(cand, response.getBody());

        Mockito.when(candidateService.createNewCand(Mockito.any(CandidateRequest.class))).thenReturn(null);
        ResponseEntity<CandidateData> response2 = candidateController.createNewCand(candreq);
        Assertions.assertEquals(HttpStatus.CONFLICT, response2.getStatusCode());

    }

    @Test
    void getAllCandidatesTest(){
        CandidateData cand = new CandidateData();
        List<CandidateData> candarray = new ArrayList<>();
        candarray.add(cand);

        Mockito.when(candidateService.getAllCandidates()).thenReturn(candarray);
        ResponseEntity<List<CandidateData>> response = candidateController.getAllCandidates();
        Assertions.assertEquals(candarray, response.getBody());

        candarray.clear();

        Mockito.when(candidateService.getAllCandidates()).thenReturn(candarray);
        ResponseEntity<List<CandidateData>> response2 = candidateController.getAllCandidates();
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }

    @Test
    void changeStatusTest(){
        CandidateData cand = new CandidateData();
        CandidateRequest candreq = new CandidateRequest();

        Mockito.when(candidateService.changeStatus(Mockito.any())).thenReturn(cand);
        ResponseEntity<CandidateData> response = candidateController.changeStatus(candreq);
        Assertions.assertEquals(cand, response.getBody());

        Mockito.when(candidateService.changeStatus(Mockito.any())).thenReturn(null);
        ResponseEntity<CandidateData> response2 = candidateController.changeStatus(candreq);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }

    @Test
    void updateCandidateTest(){
        CandidateData cand = new CandidateData();
        CandidateRequest candreq = new CandidateRequest();

        Mockito.when(candidateService.updateCandidate(Mockito.any(CandidateRequest.class))).thenReturn(cand);
        ResponseEntity<CandidateData> response = candidateController.updateCandidate(candreq);
        Assertions.assertEquals(cand, response.getBody());

        Mockito.when(candidateService.updateCandidate(Mockito.any(CandidateRequest.class))).thenReturn(null);
        ResponseEntity<CandidateData> response2 = candidateController.updateCandidate(candreq);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }

}
