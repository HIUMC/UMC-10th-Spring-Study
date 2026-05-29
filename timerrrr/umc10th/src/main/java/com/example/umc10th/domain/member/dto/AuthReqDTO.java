package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthReqDTO {

    // 로그인
    public record Login(
            @NotBlank(message = "이메일 입력은 필수입니다.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호 입력은 필수입니다.")
            String password
    ) {}
}
