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
import com.accolite.morganUI.service.CandidateService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateRepo candidateRepo;

    @Autowired
    LocationRepo locationRepo;

    @Autowired
    InstitutionRepo institutionRepo;

    @Autowired
    SkillRepo skillRepo;

    @Override
    public CandidateData createNewCand(CandidateRequest candreq){
        if(!(candidateRepo.findByemail(candreq.getEmail()).isPresent())) {
            CandidateData cand = new CandidateData(candreq.getFirstName(),candreq.getLastName(), candreq.getEmail());
            Optional<LocationData> loco = locationRepo.findById(candreq.getLocationId());
            Optional<InstitutionData> inst = institutionRepo.findById(candreq.getInstId());
            if (loco.isPresent() & inst.isPresent()) {
                cand.setLocoData(loco.get());
                cand.setInstData(inst.get());
                cand.setStatus(true);
            }
            else{
                return null;
            }
            //log.info(candreq.getSkillList().toString());

            CandidateData savedCandidate = candidateRepo.save(cand);
            Long candId = savedCandidate.getCandId();
            candreq.getSkillList().forEach(skill -> skillRepo.save(new SkillData(candId,skill)));
            Set<SkillData> skillpush = skillRepo.findBycandId(candId);
            cand.setSkillSet(skillpush);
            CandidateData savedCandidateFinal = candidateRepo.save(cand);
            return savedCandidateFinal;
        }
        return null;
    }

    @Override
    public List<CandidateData> getAllCandidates(){
        List<CandidateData> candidateDataList = candidateRepo.findAll();
        return candidateDataList;
    }

    @Override
    public CandidateData changeStatus(String candEmail){
        Optional<CandidateData> cand = candidateRepo.findByemail(candEmail);
        if(cand.isPresent()){
            CandidateData updateCand = cand.get();
            updateCand.setStatus(!updateCand.getStatus());
            return candidateRepo.save(updateCand);
        }
        else {
            return null;
        }
    }

    @Override
    public CandidateData updateCandidate(CandidateRequest candreq){
        Optional<CandidateData> cand = candidateRepo.findByemail(candreq.getEmail());
        if(cand.isPresent()){
            CandidateData updateCand = cand.get();
            updateCand.setFirstName(candreq.getFirstName());
            updateCand.setLastName(candreq.getLastName());
            Optional<LocationData> loco = locationRepo.findById(candreq.getLocationId());
            Optional<InstitutionData> inst = institutionRepo.findById(candreq.getInstId());
            if (loco.isPresent() & inst.isPresent()) {
                updateCand.setLocoData(loco.get());
                updateCand.setInstData(inst.get());
            }
            else{
                return null;
            }
            Long candId = updateCand.getCandId();
            Set<SkillData> skilldelete = skillRepo.findBycandId(candId);
            skilldelete.forEach( skill -> skillRepo.delete(skill));
            candreq.getSkillList().forEach(skill -> skillRepo.save(new SkillData(candId,skill)));
            Set<SkillData> skillpush = skillRepo.findBycandId(candId);
            updateCand.setSkillSet(skillpush);
            return candidateRepo.save(updateCand);
        }
        else {
            return null;
        }
    }
}
