package com.example.umt10th.global.security.service;

import com.example.umt10th.domain.member.converter.MemberConverter;
import com.example.umt10th.domain.member.entity.Member;
import com.example.umt10th.domain.member.enums.SocialType;
import com.example.umt10th.domain.member.exception.MemberException;
import com.example.umt10th.domain.member.exception.code.MemberErrorCode;
import com.example.umt10th.domain.member.repository.MemberRepository;
import com.example.umt10th.global.security.dto.KakaoDTO;
import com.example.umt10th.global.security.dto.OAuthDTO;
import com.example.umt10th.global.security.entity.OAuthMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // AccessToken을 이용해 Resource Server에게서 정보 조회 & 유저 객체 생성
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 유저 객체에서 정보 추출
        SocialType providerId;
        String socialUid;
        Map<String, Object> attributes = oAuth2User.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) attributes.get("profile");
        
        try {
            providerId = SocialType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
            socialUid = String.valueOf((Long)oAuth2User.getAttribute("id"));
        } catch (IllegalArgumentException e){
            throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }

        // OAuth 공통 정보 DTO로 매핑. 추후 네이버 등 다른 providerId가 추가될 경우를 위해
        OAuthDTO dto;
        switch (providerId){
            case KAKAO -> {
                String email = attributes.get("email").toString();
                String name = profile.get("nickname").toString();
                dto = new KakaoDTO(socialUid, email, name);
            }
            default -> throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }

        // DB 저장: 있다면 그 데이터 가져오고 없으면 새로 저장
        Member member = memberRepository.findBySocialTypeAndSocialUid(providerId, socialUid)
                .orElseGet(() -> {
                    Member newMember = MemberConverter.toMember(dto);
                    memberRepository.save(newMember);
                    return newMember;
                });
        log.info("KAKAO = {}, {}, {}", member.getEmail(), member.getName(), member.getSocialType());

        return new OAuthMember(member, oAuth2User.getAttributes());
    }
}
