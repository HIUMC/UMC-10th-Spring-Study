package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.enums.Gender;
import com.example.demo.domain.member.enums.Preference;

import java.time.LocalDate;

public class MemberReqDTO {
    // 마이 페이지
    public record GetInfo(
            Long id
    ) {}

    public record SignupRequest(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            Preference[] preferences,
            boolean agreement
    ){}


}
