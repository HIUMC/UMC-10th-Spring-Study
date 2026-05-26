package com.example.umc10th.global.security.dto;

import com.example.umc10th.domain.member.enums.SocialType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KaKaoDTO implements OAuthDTO{

    private final String id;
    private final String email;
    private final String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSocialUid() {
        return id;
    }

    @Override
    public String getSocialEmail() {
        return email;
    }

    @Override
    public SocialType getSocialType() {
        return SocialType.KAKAO;
    }
}
