package com.example.week4.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

public class UserResDTO {

    @Getter
    @Builder
    public static class MyPageResponse {
        private Long userId;
        private String name;
        private String email;
        private String phoneNumber;
        private Boolean phoneVerified;
        private Integer userPoint;
    }

    @Getter
    @Builder
    public static class SignUpResponse {
        private Long userId;
        private String name;
        private String email;
    }
}
