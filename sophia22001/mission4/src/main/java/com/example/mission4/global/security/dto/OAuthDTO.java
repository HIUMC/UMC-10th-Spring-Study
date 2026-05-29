package com.example.mission4.global.security.dto;

import com.example.mission4.domain.member.enums.SocialType;

/**
 * 여러 OAuth 제공자에 따라 정보가 다를 것을 대비한 DTO
 */
public interface OAuthDTO {
    SocialType getSocialType();
    String getSocialUid();
    String getSocialEmail();
    String getName();
}
