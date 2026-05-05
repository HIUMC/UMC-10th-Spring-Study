package com.example.umc10th.domain.term.dto;

public class TermReqDTO {

    public record CreateTerm(
            String termName,
            String termContent,
            Boolean isNecessary,
            Boolean isActive
    ) {
    }

    public record UpdateTerm(
            String termName,
            String termContent,
            Boolean isNecessary,
            Boolean isActive
    ) {
    }
}
