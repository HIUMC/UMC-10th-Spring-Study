package com.example.umc10th.domain.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Long point
    ){}


    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt
            ) {
    }
}