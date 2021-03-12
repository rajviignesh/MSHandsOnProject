package com.accolite.morganUI.controller;


import com.accolite.morganUI.DTO.InstitutionRequest;
import com.accolite.morganUI.entity.InstitutionData;
import com.accolite.morganUI.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/institution")
public class InstitutionController {

    @Autowired
    InstitutionService institutionService;

    @PostMapping("/create")
    public ResponseEntity<InstitutionData> createNewInst(@RequestBody InstitutionRequest instreq) {
        InstitutionData response = institutionService.createNewInst(instreq);
        if(response == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/allInsts")
    public ResponseEntity<List<InstitutionData>> getAllInstitutions(){
        List<InstitutionData> response = institutionService.getAllInsts();
        if(!response.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
