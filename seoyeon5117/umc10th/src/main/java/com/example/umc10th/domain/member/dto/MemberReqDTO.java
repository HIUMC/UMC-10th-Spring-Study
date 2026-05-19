package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.global.enums.Food;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

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
            List<TermAgreement> termAgreements
    ) {
    }

    public record TermAgreement(
            Long termId,
            Boolean isAgreed
    ) {
    }

    // 마이페이지
    public record GetInfo(Long id) {
    }

    public record UpdateInfo(
            Long id,
            String nickname,
            String profileUrl,
            String phoneNumber
    ) {
    }

    public record GetPoint(Long id) {
    }
}
