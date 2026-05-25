package com.example.week4.domain.user.converter;

import com.example.week4.domain.user.dto.UserResDTO;
import com.example.week4.domain.user.entity.User;
import com.example.week4.global.security.dto.OAuthDTO;

public class UserConverter {
    public static UserResDTO.SignUpResponse toSignUpResponse(User user) {
        return UserResDTO.SignUpResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static UserResDTO.LoginResponse toLoginResponse(String accessToken) {
        return UserResDTO.LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    public static UserResDTO.MyPageResponse toMyPageResponse(User user) {
        return UserResDTO.MyPageResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .phoneVerified(user.getPhoneVerified())
                .userPoint(user.getUserPoint())
                .build();
    }

    public static User toUser(OAuthDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password("OAUTH_USER")
                .birthDate(java.time.LocalDate.of(2000, 1, 1))
                .address("소셜 로그인 사용자")
                .gender(com.example.week4.domain.user.enums.Gender.MALE)
                .phoneNumber("010-0000-0000")
                .phoneVerified(false)
                .userPoint(0)
                .socialProvider(dto.getSocialProvider())
                .socialLoginId(dto.getSocialLoginId())
                .build();
    }
}