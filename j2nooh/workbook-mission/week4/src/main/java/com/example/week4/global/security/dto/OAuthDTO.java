package com.example.week4.global.security.dto;

import com.example.week4.domain.user.enums.SocialProvider;

public interface OAuthDTO {

    String getSocialLoginId();
    String getEmail();
    String getName();

    SocialProvider getSocialProvider();
}
