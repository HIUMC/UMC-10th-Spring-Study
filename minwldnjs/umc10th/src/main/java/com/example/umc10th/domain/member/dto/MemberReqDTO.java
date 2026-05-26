package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class MemberReqDTO {

    @Getter
    public static class JoinDTO {
        private String name;
        private String gender;
        private String birthDate;
        private String address;
        private String preferredFoodIds;

        @NotBlank(message = "이메일은 필수입니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
    }

    @Getter
    public static class LoginDTO {
        @NotBlank(message = "이메일은 필수입니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
    }
}