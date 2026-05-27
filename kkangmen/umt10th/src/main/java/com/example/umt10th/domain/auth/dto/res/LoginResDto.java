package com.example.umt10th.domain.auth.dto.res;

import lombok.Builder;

public class LoginResDto {

    @Builder
    public record LoginRes(
            String accessToken
    ){}
}
