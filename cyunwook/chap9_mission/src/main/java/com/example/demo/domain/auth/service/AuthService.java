package com.example.demo.domain.auth.service;

import com.example.demo.domain.auth.dto.AuthReqDTO;
import com.example.demo.domain.auth.dto.AuthResDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.security.entity.AuthMember;
import com.example.demo.global.security.service.CustomUserDetailsService;
import com.example.demo.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Transactional
    public Long signUp(AuthReqDTO.SignUp request) {
        Member member = Member.builder()
                .name(request.getName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .address(request.getAddress())
                .detailedAddress(request.getDetailAddress())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // BCrypt 암호화
                .point(0)
                .build();

        return memberRepository.save(member).getId();
    }

    @Transactional(readOnly = true)
    public AuthResDTO.LoginResult login(AuthReqDTO.Login request) {
        // 이메일로 멤버 조회
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일입니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // AuthMember 생성 후 토큰 발행
        AuthMember authMember = new AuthMember(member);
        String token = jwtUtil.createAccessToken(authMember);
        return new AuthResDTO.LoginResult(token);
    }
}