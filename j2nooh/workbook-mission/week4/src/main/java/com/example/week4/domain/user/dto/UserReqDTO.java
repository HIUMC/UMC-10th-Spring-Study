package com.example.week4.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UserReqDTO {
    // 회원가입
    public record SignUpRequest(
            @NotBlank(message = "이름은 필수입니다.")
            String name,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            String password,

            @NotNull(message = "생년월일은 필수입니다.")
            LocalDate birthDate,

            @NotBlank(message = "주소는 필수입니다.")
            String address,

            @NotBlank(message = "성별은 필수입니다.")
            String gender,

            @NotBlank(message = "전화번호는 필수입니다.")
            String phoneNumber
    ) {
    }

    // 마이페이지
    public record MyPageRequest(
            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId
    ) {
    }

    // 마이페이지 수정
    public record UpdateMyPageRequest(
            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId,

            @NotBlank(message = "유저 이름이 비어있으면 안 됩니다.")
            String name
    ) {
    }
}
