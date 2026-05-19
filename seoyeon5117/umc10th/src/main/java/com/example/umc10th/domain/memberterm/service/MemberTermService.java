package com.example.umc10th.domain.memberterm.service;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.memberterm.dto.MemberTermResDTO;
import com.example.umc10th.domain.memberterm.entity.MemberTerm;
import com.example.umc10th.domain.memberterm.repository.MemberTermRepository;
import com.example.umc10th.domain.term.entity.Term;
import com.example.umc10th.domain.term.enums.TermErrorCode;
import com.example.umc10th.domain.term.exception.TermException;
import com.example.umc10th.domain.term.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberTermService {

    private final TermRepository termRepository;
    private final MemberTermRepository memberTermRepository;

    public void saveTermAgreements(Member member, List<MemberReqDTO.TermAgreement> termAgreements) {
        if (termAgreements == null || termAgreements.isEmpty()) return;

        List<MemberTerm> memberTerms = termAgreements.stream()
                .map(ta -> {
                    Term term = termRepository.findById(ta.termId())
                            .orElseThrow(() -> new TermException(TermErrorCode.TERM_NOT_FOUND));
                    return MemberTerm.builder()
                            .member(member)
                            .term(term)
                            .isAgreed(ta.isAgreed())
                            .build();
                })
                .toList();
        memberTermRepository.saveAll(memberTerms);
    }

    public MemberTermResDTO.AgreeTerm agreeTerm() {
        return null;
    }

    public List<MemberTermResDTO.GetMemberTerm> getMemberTerms() {
        return null;
    }
}
