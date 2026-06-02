package com.example.umc10th.global.security.oauth;

import com.example.umc10th.domain.common.enums.Address;
import com.example.umc10th.domain.common.enums.Gender;
import com.example.umc10th.domain.common.enums.SocialType;
import com.example.umc10th.domain.user.entity.User;

import java.time.LocalDate;
import java.util.Map;

public record KakaoOAuthDTO(
        String socialUid,
        String email,
        String name
) implements OAuthDTO {


    @Override
    public SocialType getSocialType(){
        return SocialType.KAKAO;
    }

    @Override
    public String getSocialUid(){
        return socialUid;
    }

    @Override
    public String getSocialEmail(){
        return email;
    }

    @Override
    public String getName(){
        return name;
    }

    public static KakaoOAuthDTO of(Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return new KakaoOAuthDTO(
                String.valueOf(attributes.get("id")),
                (String) kakaoAccount.get("email"),
                (String) profile.get("nickname")
        );
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .socialType(SocialType.KAKAO)
                .socialUid(socialUid)
                .gender(Gender.MALE)       // 기본값, 필요시 수정
                .birthDate(LocalDate.now()) // 기본값, 필요시 수정
                .address(Address.SEOUL)    // 기본값, 필요시 수정
                .point(0)
                .build();
    }


}
