package com.accolite.morganUI.service;



import com.accolite.morganUI.DTO.InstitutionRequest;
import com.accolite.morganUI.entity.InstitutionData;

import java.util.List;

public interface InstitutionService {
    public InstitutionData createNewInst(InstitutionRequest instreq);
    public List<InstitutionData> getAllInsts();
}
