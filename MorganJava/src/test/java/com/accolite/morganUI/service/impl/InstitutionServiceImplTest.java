package com.accolite.morganUI.service.impl;

import com.accolite.morganUI.DTO.InstitutionRequest;
import com.accolite.morganUI.entity.CandidateData;
import com.accolite.morganUI.entity.InstitutionData;
import com.accolite.morganUI.repository.CandidateRepo;
import com.accolite.morganUI.repository.InstitutionRepo;
import com.accolite.morganUI.repository.LocationRepo;
import com.accolite.morganUI.repository.SkillRepo;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class InstitutionServiceImplTest {

    InstitutionData inst = new InstitutionData();
    InstitutionRequest instreq = new InstitutionRequest();
    List<InstitutionData> instarray = new ArrayList<>();

    @InjectMocks
    @Spy
    InstitutionServiceImpl institutionServiceimpl;

    @Mock
    InstitutionRepo institutionRepo;

    @Mock
    ModelMapper modelMapper;

    @Test
    void createNewInstTest(){
        Mockito.when(institutionRepo.findByInstName(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(modelMapper.map(Mockito.any(InstitutionRequest.class),eq(InstitutionData.class))).thenReturn(inst);
        Mockito.when(institutionRepo.save(Mockito.any(InstitutionData.class))).thenReturn(inst);
        InstitutionData response = institutionServiceimpl.createNewInst(instreq);
        Assertions.assertEquals(inst, response);

        Mockito.when(institutionRepo.findByInstName(Mockito.any())).thenReturn(Optional.of(inst));
        InstitutionData response2 = institutionServiceimpl.createNewInst(instreq);
        Assertions.assertEquals(null, response2);
    }

    @Test
    void getAllInsts(){
        Mockito.when(institutionRepo.findAll()).thenReturn(instarray);
        List<InstitutionData> response = institutionServiceimpl.getAllInsts();
        Assertions.assertEquals(instarray, response);
    }
}
