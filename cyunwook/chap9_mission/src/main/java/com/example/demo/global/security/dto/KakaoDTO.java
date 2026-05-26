package com.example.demo.global.security.dto;

import com.example.demo.domain.member.enums.SocialType;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class KakaoDTO implements OAuthDTO{

    private final String id;
    private final String email;
    private final String name;

    @Override
    public SocialType getSocialType() {
        return SocialType.KAKAO;
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
    public String getName() {
        return name;
    }
}
