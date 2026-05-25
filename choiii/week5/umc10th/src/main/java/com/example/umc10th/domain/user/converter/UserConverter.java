package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.request.UserCreateRequest;
import com.example.umc10th.domain.user.dto.response.UserResponse;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.global.security.entity.AuthMember;

public class UserConverter {

    private UserConverter() {}

    public static User toEntity(UserCreateRequest request, String encodedPassword) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(encodedPassword)
                .gender(request.gender())
                .birthDate(request.birthDate())
                .address(request.address())
                .point(0)
                .profileUrl(request.profileUrl())
                .phoneNumber(request.phoneNumber())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(), user.getName(), user.getEmail(), user.getGender(), user.getBirthDate(), user.getAddress(),
                user.getPoint(), user.getProfileUrl(), user.getPhoneNumber(),
                  user.getCreatedAt()
        );
    }

    public static UserResponse.Login toLoginResponse(String accessToken) {
        return new UserResponse.Login(accessToken);
    }
}
