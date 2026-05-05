package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // 회원가입
    public record Signup(
            String email,
            String password,
            String name,
            LocalDate birthDate,
            Gender gender,
            String address,
            String phoneNumber,
            List<Long> foodCategoryIds
    ) {}
}
