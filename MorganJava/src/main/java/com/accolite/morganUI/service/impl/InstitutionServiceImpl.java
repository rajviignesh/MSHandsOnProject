package com.accolite.morganUI.service.impl;

import com.accolite.morganUI.DTO.InstitutionRequest;
import com.accolite.morganUI.entity.InstitutionData;
import com.accolite.morganUI.repository.InstitutionRepo;
import com.accolite.morganUI.service.InstitutionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    InstitutionRepo institutionRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public InstitutionData createNewInst(InstitutionRequest instreq){
        if (!(institutionRepo.findByInstName(instreq.getInstName()).isPresent())) {
            InstitutionData inst = modelMapper.map(instreq, InstitutionData.class);
            InstitutionData savedInst = institutionRepo.save(inst);
            return savedInst;
        }
        return null;
    }

    @Override
    public List<InstitutionData> getAllInsts(){
        List<InstitutionData> instDataList = institutionRepo.findAll();
        return instDataList;
    }
}
