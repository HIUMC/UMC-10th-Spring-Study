package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.enums.Gender;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MemberRequestDTO {

    @Getter
    public static class SignUpRequest {
        @NotBlank(message = "이름은 필수입니다.")
        private String name;

        @NotNull(message = "성별은 필수입니다.")
        private Gender gender;

        @NotNull(message = "생년월일은 필수입니다.")
        private LocalDate birth;

        @NotBlank(message = "주소는 필수입니다.")
        private String address;

        @AssertTrue(message = "약관 동의는 필수입니다.")
        private Boolean agreement;

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank(message = "전화번호는 필수입니다.")
        private String number;

        @NotBlank(message = "닉네임은 필수입니다.")
        private String nickname;
    }

    @Getter
    public static class MeRequest {
        private Long memberId;
    }

    @Getter
    public static class HomeSummaryRequest {
        private Long memberId;
        private Long regionId;
    }
}
