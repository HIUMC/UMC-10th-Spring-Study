package com.example.umc10th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {

    /* ───────────── 로그인 ───────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Login {
        private String accessToken;
    }

    /* ───────────── 회원가입 ───────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignupResDTO {
        private Long memberId;
        private String userId;
        private String nickname;
    }

    /* ───────────── 내 정보 조회 ───────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetInfo {
        private Long memberId;
        private String userId;
        private String name;
        private String nickname;
        private String gender;
        private String birthDate;
        private String address;
    }

    /* ───────────── 닉네임 수정 ───────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    public static class NicknameUpdateResDTO {
        private Long memberId;
        private String nickname;
    }

    /* ───────────── 알림 설정 ───────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    public static class NoticeSettingResDTO {
        private boolean isReviewPushEnabled;
        private boolean newEventAlarmEnabled;
        private boolean qnaAnswerAlarmEnabled;
    }
}
