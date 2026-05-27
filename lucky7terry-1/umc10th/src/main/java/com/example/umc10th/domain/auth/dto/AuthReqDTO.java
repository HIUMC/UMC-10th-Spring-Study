package com.example.umc10th.domain.auth.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class AuthReqDTO {

    // 회원가입
    public record SignupDTO(

            @NotNull(message = "약관 동의 정보를 입력해주세요.")
            AgreeDTO agree,

            @NotBlank(message = "이름을 입력하세요.")
            String name,

            @NotNull(message = "성별을 입력하세요.")
            Gender gender,

            @NotNull(message = "생년월일을 입력하세요.")
            LocalDate birthDate,

            @NotNull(message = "주소를 입력하세요.")
            Address address,

            String detailAddress,

            List<Long> preferCategoryIds,

            @NotBlank(message = "이메일 주소를 입력하세요.")
            String email,

            @NotBlank(message = "비밀번호를 입력하세요.")
            String password

    ){}

    public record AgreeDTO(
            @NotNull(message = "만 14세 이상 여부를 입력해주세요.")
            Boolean age,

            @NotNull(message = "서비스 이용약관 동의 여부를 입력해주세요.")
            Boolean service,

            @NotNull(message = "개인정보 처리방침 동의 여부를 입력해주세요.")
            Boolean privacy,

            Boolean location,

            Boolean marketing
    ) {}

    public record LoginDTO(
            String email,
            String password
    ) {}

}
