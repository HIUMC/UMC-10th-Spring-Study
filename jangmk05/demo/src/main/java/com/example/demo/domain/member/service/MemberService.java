package com.example.demo.domain.member.service;

import com.example.demo.domain.member.converter.MemberConverter;
import com.example.demo.domain.member.dto.MemberReqDTO;
import com.example.demo.domain.member.dto.MemberResDTO;
import com.example.demo.domain.member.entity.AuthMember;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.MemberException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;
import com.example.demo.domain.member.repository.*;
import com.example.demo.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public MemberResDTO.GetInfo getInfo(AuthMember authMember) {
        Member findMember = memberRepository.findByEmail(authMember.getUsername())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        findMember.getMemberFoodList().size();
        findMember.getMemberTermList().size();

        return MemberConverter.toGetInfo(findMember);
    }

    @Transactional
    public MemberResDTO.SignupResponse signup(MemberReqDTO.SignupRequest request) {

        if (memberRepository.findByEmail(request.email()).isPresent()) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        Member member = MemberConverter.toEntity(request, encodedPassword);
        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignupResponse(savedMember);
    }

    @Transactional
    public MemberResDTO.LoginResponse login(MemberReqDTO.LoginRequest request) {
        // 1. 이메일로 유저 찾기
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        // 3. Spring Security용 객체(AuthMember)로 래핑
        AuthMember authMember = new AuthMember(member);

        // 4. JWT 토큰 생성
        String accessToken = jwtUtil.createAccessToken(authMember);

        // 5. 응답 DTO 반환
        return MemberConverter.toLoginResponse(member.getId(), accessToken);
    }
}
