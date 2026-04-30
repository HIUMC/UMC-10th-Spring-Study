package com.example.umc10th.domain.term.service;

import com.example.umc10th.domain.term.dto.TermReqDTO;
import com.example.umc10th.domain.term.dto.TermResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermService {

    public TermResDTO.CreateTerm createTerm(TermReqDTO.CreateTerm dto) {
        return null;
    }

    public TermResDTO.GetTerm getTerm(Long termId) {
        return null;
    }

    public TermResDTO.UpdateTerm updateTerm(Long termId, TermReqDTO.UpdateTerm dto) {
        return null;
    }

    public void deleteTerm(Long termId) {
    }

    public List<TermResDTO.GetTerm> getActiveTerms() {
        return null;
    }
}
