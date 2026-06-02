package com.example.umc10th.global.security.service;

import com.example.umc10th.domain.common.enums.SocialType;
import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.api.code.MemberErrorCode;
import com.example.umc10th.global.exception.MemberException;
import com.example.umc10th.global.security.oauth.KakaoOAuthDTO;
import com.example.umc10th.global.security.oauth.OAuthDTO;
import com.example.umc10th.global.security.oauth.OAuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;

import java.util.Map;

import static com.example.umc10th.domain.common.enums.SocialType.KAKAO;


@Service
@RequiredArgsConstructor
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(
            OAuth2UserRequest userRequest
    ) throws OAuth2AuthenticationException {
        // (필수) 인증 서버의 일회성 토큰을 이용해 정보 조회 & 유저 객체 생성
        OAuth2User oAuthMember = super.loadUser(userRequest);

        // 유저 객체에서 정보 추출
        SocialType socialType;
        String socialUid;

        Map<String, Object> attributes = oAuthMember.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) attributes.get("profile");
        try {
            socialType = SocialType.valueOf(
                    userRequest.getClientRegistration().getRegistrationId().toUpperCase()
            );            socialUid = String.valueOf((Long) oAuthMember.getAttribute("id"));
        } catch (IllegalArgumentException e) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        // OAuth 공통 정보 DTO로 매핑
        OAuthDTO dto = switch (socialType) {
            case KAKAO -> new KakaoOAuthDTO(
                    socialUid,
                    attributes.get("email").toString(),
                    profile.get("nickname").toString()
            );
            default -> throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        };


        // DB 저장: 있다면 그 데이터 가져오고 없으면 새로 저장
        User user = userRepository.findBySocialTypeAndSocialUid(socialType, socialUid)
                .orElseGet(() -> userRepository.save(dto.toEntity()));

        return new OAuthMember(user, oAuthMember.getAttributes());
    }
}
