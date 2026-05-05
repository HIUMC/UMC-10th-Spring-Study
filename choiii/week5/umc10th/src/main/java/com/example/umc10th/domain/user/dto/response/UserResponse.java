package com.example.umc10th.domain.user.dto.response;

import com.example.umc10th.domain.common.enums.Address;
import com.example.umc10th.domain.common.enums.Gender;
import com.example.umc10th.domain.common.enums.SocialProvider;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponse(
        Long userId,
        String name,
        Gender gender,
        LocalDate birthDate,
        Address address,
        Integer point,
        String profileUrl,
        String email,
        String phoneNumber,
        SocialProvider socialProvider,
        String socialId,
        LocalDateTime createdAt
) {}
