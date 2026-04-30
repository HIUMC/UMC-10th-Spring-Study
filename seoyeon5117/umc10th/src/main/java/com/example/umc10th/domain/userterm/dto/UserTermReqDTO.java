package com.example.umc10th.domain.userterm.dto;

import java.util.List;

public class UserTermReqDTO {

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
