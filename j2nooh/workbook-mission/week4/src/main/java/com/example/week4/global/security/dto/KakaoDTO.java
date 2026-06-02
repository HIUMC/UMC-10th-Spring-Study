package com.example.week4.global.security.dto;

import com.example.week4.domain.user.enums.SocialProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoDTO implements OAuthDTO {

    private String socialLoginId;
    private String email;
    private String name;

    @Override
    public SocialProvider getSocialProvider() {
        return SocialProvider.KAKAO;
    }

}
