package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;
import lombok.Builder;

import java.time.LocalDate;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String nickname,
            String email,
            String phoneNumber,
            Integer point,
            Gender gender,
            LocalDate birthDate,
            Address address
    ){}

    @Builder
    public record SignupDTO(
            Long memberId
    ) {}
}
