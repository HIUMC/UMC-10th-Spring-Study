package com.example.week4.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserReqDTO {

    // 마이페이지
    public record MyPageRequest(
            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId
    ) {
    }

    // 마이페이지 수정
    public record UpdateMyPageRequest(
            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId,
            @NotBlank(message = "유저 이름이 비어있으면 안 됩니다.")
            String name
    ) {
    }
}
