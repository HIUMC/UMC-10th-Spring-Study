package com.example.demo.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AuthResDTO {

    @Getter
    @AllArgsConstructor
    public static class LoginResult {
        private String accessToken;
    }
}