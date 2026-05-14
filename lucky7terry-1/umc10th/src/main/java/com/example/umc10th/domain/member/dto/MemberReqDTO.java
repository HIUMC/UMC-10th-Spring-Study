package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // Request Body 예시
    public record GetInfo(Long id){}

    // 회원가입
    public record SignupDTO(
            @NotBlank(message = "이름을 입력하세요.")
            String name,

            @NotBlank(message = "닉네임을 입력하세요.")
            String nickname,

            @NotNull(message = "성별을 입력하세요.")
            Gender gender,

            @NotNull(message = "생년월일을 입력하세요.")
            LocalDate birthDate,

            @NotNull(message = "주소를 입력하세요.")
            Address address,

            @NotBlank(message = "이메일 주소를 입력하세요.")
            String email,

            @NotBlank(message = "휴대폰 번호를 입력하세요.")
            String phoneNumber,

            Boolean locationAllow,
            Boolean marketingAllow,
            List<Long> preferCategoryIds
    ) {}

}
