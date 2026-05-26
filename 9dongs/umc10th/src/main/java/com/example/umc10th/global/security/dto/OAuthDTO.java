package com.example.umc10th.global.security.dto;


import com.example.umc10th.domain.member.enums.SocialType;

public interface OAuthDTO {
    String getName();
    String getSocialUid();
    String getSocialEmail();
    SocialType getSocialType();
}
