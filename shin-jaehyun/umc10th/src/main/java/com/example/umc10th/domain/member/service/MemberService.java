package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.entity.AuthMember;
import com.example.umc10th.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원가입
    public MemberResDTO.SignupResult signup(MemberReqDTO.Signup dto) {
        String encodedPassword = passwordEncoder.encode(dto.password());

        Member member = MemberConverter.toMember(dto, encodedPassword);

        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignupResult(savedMember);
    }

    // 마이페이지
    public MemberResDTO.GetInfo getInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    public MemberResDTO.GetInfo getInfo(AuthMember member) {
        return MemberConverter.toGetInfo(member.getMember());
    }

    // 로그인
    public MemberResDTO.GetAccessToken login(MemberReqDTO.Login dto) {
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.LOGIN_FAILED);
        }

        // Security용 객체 생성
        AuthMember authMember = new AuthMember(member);

        // JWT 생성
        String accessToken = jwtUtil.createAccessToken(authMember);

        // Response DTO 반환
        return MemberConverter.toGetAccessToken(accessToken);
    }
}
