package com.accolite.morganUI.service.impl;

import com.accolite.morganUI.DTO.CandidateRequest;
import com.accolite.morganUI.entity.CandidateData;
import com.accolite.morganUI.entity.InstitutionData;
import com.accolite.morganUI.entity.LocationData;
import com.accolite.morganUI.entity.SkillData;
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
import org.springframework.http.ResponseEntity;

import java.util.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CandidateServiceImplTest {
    @InjectMocks
    @Spy
    CandidateServiceImpl candidateServiceImpl;

    @Mock
    CandidateRepo candidateRepo;

    @Mock
    LocationRepo locationRepo;

    @Mock
    InstitutionRepo institutionRepo;

    @Mock
    SkillRepo skillRepo;

    CandidateData cand = new CandidateData();
    CandidateRequest candreq = new CandidateRequest();
    List<CandidateData> candarray = new ArrayList<>();
    LocationData loco = new LocationData();
    InstitutionData inst = new InstitutionData();
    SkillData skill = new SkillData();
    Set<String> skillNames = new HashSet<>();
    Set<SkillData> skillList = new HashSet<>();

    @Test
    void createNewCandTest(){

        Mockito.when(candidateRepo.findByemail(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(locationRepo.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(institutionRepo.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(candidateRepo.save(Mockito.any(CandidateData.class))).thenReturn(null);
        CandidateData response = candidateServiceImpl.createNewCand(candreq);
        Assertions.assertEquals(null, response);

        Long x = 1l;
        cand.setCandId(x);
        skillNames.add("test");
        candreq.setSkillList(skillNames);

        Mockito.when(candidateRepo.findByemail(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(locationRepo.findById(Mockito.any())).thenReturn(Optional.of(loco));
        Mockito.when(institutionRepo.findById(Mockito.any())).thenReturn(Optional.of(inst));
        Mockito.when(candidateRepo.save(Mockito.any(CandidateData.class))).thenReturn(cand);
        Mockito.when(skillRepo.save(Mockito.any(SkillData.class))).thenReturn(skill);
        Mockito.when(skillRepo.findBycandId(Mockito.any())).thenReturn(skillList);
        Mockito.when(candidateRepo.save(Mockito.any(CandidateData.class))).thenReturn(cand);
        CandidateData response2 = candidateServiceImpl.createNewCand(candreq);
        Assertions.assertEquals(cand, response2);

        Mockito.when(candidateRepo.findByemail(Mockito.any())).thenReturn(Optional.of(cand));
        CandidateData response3 = candidateServiceImpl.createNewCand(candreq);
        Assertions.assertEquals(null, response3);
    }

    @Test
    void getAllCandidatesTest(){
        Mockito.when(candidateRepo.findAll()).thenReturn(candarray);
        List<CandidateData> response = candidateServiceImpl.getAllCandidates();
        Assertions.assertEquals(candarray, response);
    }

    @Test
    void changeStatusTest(){

        cand.setStatus(true);
        Mockito.when(candidateRepo.findByemail(Mockito.any())).thenReturn(Optional.of(cand));
        Mockito.when(candidateRepo.save(Mockito.any(CandidateData.class))).thenReturn(cand);
        CandidateData response = candidateServiceImpl.changeStatus(candreq.getEmail());
        Assertions.assertEquals(cand, response);

        Mockito.when(candidateRepo.findByemail(Mockito.any())).thenReturn(Optional.empty());
        CandidateData response2 = candidateServiceImpl.changeStatus(candreq.getEmail());
        Assertions.assertEquals(null, response2);
    }

    @Test
    void updateCandidateTest(){
        Mockito.when(candidateRepo.findByemail(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(locationRepo.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(institutionRepo.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(candidateRepo.save(Mockito.any(CandidateData.class))).thenReturn(null);
        CandidateData response = candidateServiceImpl.updateCandidate(candreq);
        Assertions.assertEquals(null, response);

        Mockito.when(candidateRepo.findByemail(Mockito.any())).thenReturn(Optional.of(cand));
        CandidateData response3 = candidateServiceImpl.updateCandidate(candreq);
        Assertions.assertEquals(null, response3);

        Long x = 1l;
        cand.setCandId(x);
        skillNames.add("test");
        candreq.setSkillList(skillNames);
        skillList.add(skill);

        Mockito.when(candidateRepo.findByemail(Mockito.any())).thenReturn(Optional.of(cand));
        Mockito.when(locationRepo.findById(Mockito.any())).thenReturn(Optional.of(loco));
        Mockito.when(institutionRepo.findById(Mockito.any())).thenReturn(Optional.of(inst));
        Mockito.when(skillRepo.findBycandId(Mockito.any())).thenReturn(skillList);
        Mockito.doNothing().when(skillRepo).delete(Mockito.any(SkillData.class));
        Mockito.when(skillRepo.save(Mockito.any(SkillData.class))).thenReturn(skill);
        Mockito.when(skillRepo.findBycandId(Mockito.any())).thenReturn(skillList);
        Mockito.when(candidateRepo.save(Mockito.any(CandidateData.class))).thenReturn(cand);
        CandidateData response2 = candidateServiceImpl.updateCandidate(candreq);
        Assertions.assertEquals(cand, response2);
    }
}
