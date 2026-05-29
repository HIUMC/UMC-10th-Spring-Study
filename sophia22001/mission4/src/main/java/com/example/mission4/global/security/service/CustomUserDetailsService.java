package com.example.mission4.global.security.service;

import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.member.enums.SocialType;
import com.example.mission4.domain.member.exception.MemberException;
import com.example.mission4.domain.member.exception.code.MemberErrorCode;
import com.example.mission4.domain.member.repository.MemberRepository;
import com.example.mission4.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * DB를 통해 기존 회원인지 확인하기 위한 서비스
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final MemberRepository memberRepository;

    // 1. username & pasaword 버전
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Member member = memberRepository.findByEmail(username)
//                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
//
//        return new AuthMember(member);
//    }

    // 2. 소셜 로그인 버전
    public UserDetails loadUserByUidAndSocialType(
            SocialType socialType,
            String username
    ) throws UsernameNotFoundException {
        // DB에서 기존 회원 정보 조회 & 인증 객체 생성
        Member member = memberRepository.findBySocialTypeAndSocialUid(socialType, username)
            .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return new AuthMember(member);
    }
}
