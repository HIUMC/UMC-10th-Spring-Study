package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.request.UserCreateRequest;
import com.example.umc10th.domain.user.dto.response.UserResponse;
import com.example.umc10th.domain.user.entity.User;

public class UserConverter {

    private UserConverter() {}

    public static User toEntity(UserCreateRequest request) {
        return User.builder()
                .name(request.name())
                .gender(request.gender())
                .birthDate(request.birthDate())
                .address(request.address())
                .point(0)
                .profileUrl(request.profileUrl())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .socialProvider(request.socialProvider())
                .socialId(request.socialId())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(), user.getName(), user.getGender(), user.getBirthDate(), user.getAddress(),
                user.getPoint(), user.getProfileUrl(), user.getEmail(), user.getPhoneNumber(),
                user.getSocialProvider(), user.getSocialId(), user.getCreatedAt()
        );
    }
}
