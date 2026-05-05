package com.example.umc10th.domain.memberterm.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberTermResDTO {

    @Builder
    public record AgreeTerm(
            List<Long> memberTermIds,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record GetMemberTerm(
            Long memberTermId,
            Long termId,
            Boolean isAgreed
    ) {
    }
}
