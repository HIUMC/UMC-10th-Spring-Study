package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.AuthReqDTO;
import com.example.umc10th.domain.member.dto.AuthResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 로그인
    public AuthResDTO.Login login(AuthReqDTO.Login dto) {
        // 1. 이메일로 회원 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new ProjectException(MemberErrorCode.NOT_FOUND));

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new ProjectException(MemberErrorCode.NOT_FOUND);
        }

        // 3. AccessToken 발급
        AuthMember authMember = new AuthMember(member);
        String accessToken = jwtUtil.createAccessToken(authMember);

        return AuthResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }
}
