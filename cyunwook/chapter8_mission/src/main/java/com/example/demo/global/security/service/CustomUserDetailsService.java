package com.example.demo.global.security.service;

import com.example.demo.domain.member.entity.Member;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일의 회원이 없습니다: " + email));
        return new AuthMember(member);
    }
}