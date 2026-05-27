package com.example.umt10th.global.security.service;

import com.example.umt10th.domain.member.entity.Member;
import com.example.umt10th.domain.member.enums.SocialType;
import com.example.umt10th.domain.member.exception.MemberException;
import com.example.umt10th.domain.member.exception.code.MemberErrorCode;
import com.example.umt10th.domain.member.repository.MemberRepository;
import com.example.umt10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 회원을 찾는다.
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 찾은 회원을 AuthMember(인증 객체)로 만든다.
        return new AuthMember(member);
    }

    public UserDetails loadUserByUidAndSocialType(SocialType socialType, String username) throws UsernameNotFoundException{

        // DB에서 기존 회원 정보 조회 & 인증 객체 생성
        Member member = memberRepository.findBySocialTypeAndSocialUid(socialType, username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return new AuthMember(member);
    }
}
