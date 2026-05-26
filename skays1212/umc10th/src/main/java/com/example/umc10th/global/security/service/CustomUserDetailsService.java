package com.example.umc10th.global.security.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Member not found: " + username));
        return new AuthMember(member);
    }

    public UserDetails loadUserByUidAndSocialType(
            SocialType socialType,
            String uid
    ) throws UsernameNotFoundException {
        if (socialType == SocialType.LOCAL) {
            return loadUserByUsername(uid);
        }

        Member member = memberRepository.findBySocialTypeAndSocialUid(socialType, uid)
                .orElseThrow(() -> new UsernameNotFoundException("Member not found: " + uid));
        return new AuthMember(member);
    }
}
