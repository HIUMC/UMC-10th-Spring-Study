package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class MemberReqDTO {

    //마이페이지, 홈 화면 상단
    public record GetInfo(
            Long id
    ){}

    //회원가입
    public record SignUp(
            String name,
            Gender gender,
            LocalDate birthDate,
            String nickname,
            String email,
            String phoneNumber,
            String address,
            String detailAddress,
            String townName
    ){}
}
