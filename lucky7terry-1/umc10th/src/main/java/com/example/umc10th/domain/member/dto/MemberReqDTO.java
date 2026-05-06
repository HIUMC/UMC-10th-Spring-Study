package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;

import java.util.List;

public class MemberReqDTO {

    // Request Body 예시
    public record GetInfo(Long id){}

    // 회원가입
    public record SignupDTO(
            String name,
            String nickname,
            Gender gender,
            String birthDate,
            Address address,
            String email,
            String phoneNumber,
            Boolean locationAllow,
            Boolean marketingAllow,
            List<Long> preferCategoryIds
    ) {}

}
