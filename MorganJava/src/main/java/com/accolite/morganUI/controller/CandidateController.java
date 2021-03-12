package com.accolite.morganUI.controller;


import com.accolite.morganUI.DTO.CandidateRequest;
import com.accolite.morganUI.entity.CandidateData;
import com.accolite.morganUI.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @PostMapping("/create")
    public ResponseEntity<CandidateData> createNewCand(@RequestBody CandidateRequest candreq) {
        CandidateData response = candidateService.createNewCand(candreq);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @GetMapping("/get/allcandidates")
    public ResponseEntity<List<CandidateData>> getAllCandidates(){
        List<CandidateData> response = candidateService.getAllCandidates();
        if(!response.isEmpty()){
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @PostMapping("/updateStatus")
    public ResponseEntity<CandidateData> changeStatus(@RequestBody CandidateRequest candReq){
        CandidateData response = candidateService.changeStatus(candReq.getEmail());
        if(response != null){
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/updateCandidate")
    public ResponseEntity<CandidateData> updateCandidate(@RequestBody CandidateRequest candReq){
        CandidateData response = candidateService.updateCandidate(candReq);
        if(response != null){
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
