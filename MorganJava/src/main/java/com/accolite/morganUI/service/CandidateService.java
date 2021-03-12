package com.accolite.morganUI.service;

import com.accolite.morganUI.DTO.CandidateRequest;
import com.accolite.morganUI.entity.CandidateData;

import java.util.List;

public interface CandidateService {
    public CandidateData createNewCand(CandidateRequest cand);
    public List<CandidateData> getAllCandidates();
    public CandidateData changeStatus(String candEmail);
    public CandidateData updateCandidate(CandidateRequest cand);
}
