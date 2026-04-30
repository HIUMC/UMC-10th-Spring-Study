package com.example.umc10th.domain.userterm.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class UserTermResDTO {

    @Builder
    public record AgreeTerm(
            List<Long> userTermIds,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record GetUserTerm(
            Long userTermId,
            Long termId,
            Boolean isAgreed
    ) {
    }
}
