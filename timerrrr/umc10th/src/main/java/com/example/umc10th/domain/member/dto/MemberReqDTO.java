package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MemberReqDTO {

    //마이페이지, 홈 화면 상단
    public record GetInfo(
            Long id
    ){}

    //회원가입
    public record SignUp(
            @NotBlank(message = "이름 입력은 필수입니다.")
            String name,
            Gender gender,
            @NotNull(message = "생년월일을 입력하세요.")
            LocalDate birthDate,
            String nickname,
            @NotBlank(message = "이메일 입력은 필수입니다.")
            String email,
            String phoneNumber,
            @NotBlank(message = "주소 입력은 필수입니다.")
            String address,
            @NotBlank(message = "상세 주소를 입력하세요.")
            String detailAddress,
            String townName
    ){}
}
