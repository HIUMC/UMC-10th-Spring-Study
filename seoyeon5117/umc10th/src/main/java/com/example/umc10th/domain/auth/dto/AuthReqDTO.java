package com.example.umc10th.domain.auth.dto;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.global.enums.Food;

import java.time.LocalDate;
import java.util.List;

public class AuthReqDTO {

    public record SignUp(
            String email,
            String password,
            String nickname,
            String phoneNumber,
            Gender gender,
            LocalDate birthday,
            String address,
            String profileUrl,
            List<Food> foodPreferences,
            List<MemberReqDTO.TermAgreement> termAgreements
    ) {
    }

    public record Login(
            String email,
            String password
    ) {
    }
}
