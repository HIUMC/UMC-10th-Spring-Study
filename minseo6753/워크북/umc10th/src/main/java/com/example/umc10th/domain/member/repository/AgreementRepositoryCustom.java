package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import java.util.List;

public interface AgreementRepositoryCustom {
    void saveAllByJdbc(Long memberId, List<MemberReqDTO.AgreementReq> agreements);
}
