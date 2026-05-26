package com.example.umc10th.domain.member.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberReqDTO {

    /* ───────────── 로그인 ───────────── */
    @Getter
    @NoArgsConstructor
    public static class LoginReqDTO {

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
    }

    /* ───────────── 회원가입 ───────────── */
    @Getter
    @NoArgsConstructor
    public static class SignupReqDTO {

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String userId;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
        private String password;

        @NotBlank(message = "이름은 필수입니다.")
        private String name;

        @NotBlank(message = "닉네임은 필수입니다.")
        private String nickname;

        @NotBlank(message = "성별은 필수입니다.")
        private String gender;

        @NotBlank(message = "생년월일은 필수입니다.")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "생년월일 형식이 올바르지 않습니다. (예: 2000-12-12)")
        private String birthDate;

        @NotBlank(message = "주소는 필수입니다.")
        private String address;

        @Valid
        @NotNull(message = "약관 동의 정보는 필수입니다.")
        private AgreementsDTO agreements;

        private String foodPreference;

        @Getter
        @NoArgsConstructor
        public static class AgreementsDTO {

            @AssertTrue(message = "이용약관 동의는 필수입니다.")
            private boolean termsOfService;

            @AssertTrue(message = "개인정보 처리방침 동의는 필수입니다.")
            private boolean privacyPolicy;

            private boolean marketingEmail;      // (선택) 마케팅 이메일 수신 동의
            private boolean locationInformation; // (선택) 위치정보 수신 동의
        }
    }

    /* ───────────── 1:1 문의 ───────────── */
    @Getter
    @NoArgsConstructor
    public static class QnaReqDTO {

        @NotBlank(message = "제목은 필수입니다.")
        private String title;

        @NotBlank(message = "내용은 필수입니다.")
        private String content;
    }

    /* ───────────── 닉네임 수정 ───────────── */
    @Getter
    @NoArgsConstructor
    public static class NicknameUpdateReqDTO {

        @NotBlank(message = "닉네임은 필수입니다.")
        private String nickname;
    }

    /* ───────────── 알림 설정 ───────────── */
    @Getter
    @NoArgsConstructor
    public static class NoticeSettingReqDTO {

        private boolean isReviewPushEnabled;
        private boolean newEventAlarmEnabled;
        private boolean qnaAnswerAlarmEnabled;
    }
}
