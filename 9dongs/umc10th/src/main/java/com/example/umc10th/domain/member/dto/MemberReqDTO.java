package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;

import java.util.List;

public class MemberReqDTO {
    public record SignUpDTO(
            String email,
            String password,
            String name,
            String nickname,
            Gender gender,
            Integer birthYear,
            Integer birthMonth,
            Integer birthDay,
            String address,
            String specAddress,
            String phoneNumber,
            List<Long> preferCategory
    ) {
    }

    public record LoginDTO(
            String email,
            String password
    ){

    }

    public record UpdateRegionDTO(
            Long regionId
    ) {
    }
}
