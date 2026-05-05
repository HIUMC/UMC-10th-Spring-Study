package com.example.umc10th.domain.memberterm.dto;

import java.util.List;

public class MemberTermReqDTO {

    public record AgreeTerm(
            Long memberId,
            List<TermAgreement> terms
    ) {
    }

    public record TermAgreement(
            Long termId,
            Boolean isAgreed
    ) {
    }
}
