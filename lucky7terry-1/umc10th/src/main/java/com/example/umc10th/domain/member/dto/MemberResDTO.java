package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;
import lombok.Builder;

import java.time.LocalDate;

public class MemberResDTO {

    @Builder
    public record MyPageDTO(
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String detailAddress,
            Integer point,
            String email,
            String phoneNumber
    ) {
    }

    @Builder
    public record SignupDTO(
            Long memberId
    ) {
    }
}

