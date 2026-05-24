package com.example.demo.domain.member.service;

import com.example.demo.domain.member.converter.MemberConverter;
import com.example.demo.domain.member.dto.MemberRequestDTO;
import com.example.demo.domain.member.dto.MemberResponseDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.enums.SocialType;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.store.entity.Region;
import com.example.demo.domain.store.repository.RegionRepository;
import global.security.AuthMember;
import global.security.jwt.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 로그인 관련 메서드
    public MemberResponseDTO.LoginResultDTO login(MemberRequestDTO.LoginRequest request) {

        // request의 email로 Member 조회하기
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));

        // request의 password와 조회한 Member의 password가 일치하지 않으면 예외 던지기
        // DB에는 암호화된 값이 들어있기 때문에 passwordEncoder로 비교해야 함
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        // 조회한 Member로 AuthMember 만들고, AuthMember로 accessToken 만들기
        AuthMember authMember = new AuthMember(member);
        String accessToken = jwtUtil.createAccessToken(authMember);

        return MemberResponseDTO.LoginResultDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .tokenType("Bearer")
                .build();
    }

    public Member getMemberProfile(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
    }

    public Region getHomeSummaryRegion(Long regionId) {
        return regionRepository.findById(regionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 지역을 찾을 수 없습니다."));
    }

    public Member joinMember(MemberRequestDTO.SignUpRequest request) {

        // 가입된 이메일이면 예외
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        // request로 들어온 password 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        // 컨버터로 분리, 단일책임원칙
        Member member = MemberConverter.toMember(request, encodedPassword);

        return memberRepository.save(member);
    }
}
