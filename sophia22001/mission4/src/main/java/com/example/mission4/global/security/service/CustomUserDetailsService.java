package com.example.mission4.global.security.service;

import com.example.mission4.domain.member.entity.Member;
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
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return new AuthMember(member);
    }
}
