package com.example.umc10th.domain.user.dto.response;

import com.example.umc10th.domain.common.enums.Address;
import com.example.umc10th.domain.common.enums.Gender;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponse(
        Long userId,
        String name,
        String email,

        Gender gender,
        LocalDate birthDate,
        Address address,
        Integer point,
        String profileUrl,
        String phoneNumber,
        LocalDateTime createdAt
) {
    // 추가
    public record Login(String accessToken) {}
}
