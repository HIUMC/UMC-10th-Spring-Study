package com.example.week4.domain.user.converter;

import com.example.week4.domain.user.dto.UserResDTO;
import com.example.week4.domain.user.entity.User;

public class UserConverter {

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
}