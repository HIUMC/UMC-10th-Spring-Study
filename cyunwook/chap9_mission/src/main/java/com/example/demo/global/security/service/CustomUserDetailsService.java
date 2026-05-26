package com.example.demo.global.security.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.enums.SocialType;
import com.example.demo.domain.member.exception.MemberException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.security.entity.AuthMember;
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
        throw new UsernameNotFoundException("loadUserByUidAndSocialType을 사용하세요.");
    }

    public UserDetails loadUserByUidAndSocialType(
            SocialType socialType,
            String uid
    ) throws UsernameNotFoundException {
        Member member = memberRepository.findBySocialTypeAndSocialUid(socialType, uid)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return new AuthMember(member);
    }
}