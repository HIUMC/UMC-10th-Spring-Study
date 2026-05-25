package com.example.demo.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignUpResultDTO {
        private Long memberId;
        private String nickname;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MeResultDTO {
        private Long memberId;
        private Long point;
        private String email;
        private String number;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class HomeSummaryResultDTO {
        private Long regionId;
        private String regionName;
        private Long point;
        private Long completionCount;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class LoginResultDTO {
        private Long memberId;
        private String accessToken;
        private String tokenType;
    }
}
