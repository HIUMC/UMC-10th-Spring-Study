package com.example.umc10th.domain.term.dto;

import lombok.Builder;

public class TermResDTO {

    @Builder
    public record CreateTerm(
            Long termId
    ) {
    }

    @Builder
    public record GetTerm(
            Long termId,
            String termName,
            String termContent,
            Boolean isNecessary,
            Boolean isActive
    ) {
    }

    @Builder
    public record UpdateTerm(
            Long termId
    ) {
    }
}
