package com.example.umc10th.global.security.oauth;

import com.example.umc10th.domain.common.enums.SocialType;
import com.example.umc10th.domain.user.entity.User;

public interface OAuthDTO {
    SocialType getSocialType();
    String getSocialUid();
    String getSocialEmail();
    String getName();
    User toEntity();
}
