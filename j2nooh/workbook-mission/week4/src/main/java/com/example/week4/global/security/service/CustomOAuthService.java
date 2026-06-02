package com.example.week4.global.security.service;

import com.example.week4.domain.mission.repository.UserMissionRepository;
import com.example.week4.domain.user.converter.UserConverter;
import com.example.week4.domain.user.entity.User;
import com.example.week4.domain.user.enums.SocialProvider;
import com.example.week4.domain.user.repository.UserRepository;
import com.example.week4.global.security.dto.KakaoDTO;
import com.example.week4.global.security.dto.OAuthDTO;
import com.example.week4.global.security.entity.OAuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(
            OAuth2UserRequest userRequest
    ) throws OAuth2AuthenticationException {

        // 1. 카카오 서버에서 사용자 정보 가져오기
        OAuth2User oAuthMember = super.loadUser(userRequest);

        // 2. 어떤 소셜 제공자인지 확인
        SocialProvider providerId;
        String socialLoginId;

        try {
            providerId = SocialProvider.valueOf(
                    userRequest.getClientRegistration()
                            .getRegistrationId()
                            .toUpperCase()
            );
            socialLoginId = String.valueOf((Long) oAuthMember.getAttribute("id"));
        } catch (IllegalArgumentException e) {
            throw new OAuth2AuthenticationException("지원하지 않는 소셜 로그인입니다.");
        }

        // 3. 카카오만 지원
        if (providerId != SocialProvider.KAKAO) {
            throw new OAuth2AuthenticationException("지원하지 않는 소셜 로그인입니다.");
        }

        // 4. 카카오 응답에서 email, nickname 추출
        Map<String, Object> kakaoAccount = oAuthMember.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        String email = String.valueOf(kakaoAccount.get("email"));
        String name = String.valueOf(profile.get("nickname"));

        // 5. OAuth 공통 정보 DTO로 매핑
        OAuthDTO dto = new KakaoDTO(socialLoginId, email, name);

        // 6. 기존 회원이면 조회, 없으면 새로 저장
        User user = userRepository
                .findBySocialProviderAndSocialLoginId(providerId, socialLoginId)
                .orElseGet(() -> {
                    User newUser = UserConverter.toUser(dto);
                    return userRepository.save(newUser);
                });

        // 7. OAuth 인증 객체 반환
        return new OAuthMember(user, oAuthMember.getAttributes());
    }
}
