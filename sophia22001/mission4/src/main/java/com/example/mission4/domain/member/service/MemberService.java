package com.example.mission4.domain.member.service;

import com.example.mission4.domain.member.converter.MemberConverter;
import com.example.mission4.domain.member.dto.MemberReqDTO;
import com.example.mission4.domain.member.dto.MemberResDTO;
import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.member.exception.MemberException;
import com.example.mission4.domain.member.exception.code.MemberErrorCode;
import com.example.mission4.domain.member.repository.MemberRepository;
import com.example.mission4.global.security.entity.AuthMember;
import com.example.mission4.global.security.util.JwtUtil;
import jakarta.validation.Valid;
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

    public MemberResDTO.GetInfo getInfo(Long memberId) {

        // DB에서 유저 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 컨버터로 응답 DTO 생성, return
        return MemberConverter.toGetInfo(member); // Entity -> dto

    }

    @Transactional
    public MemberResDTO.SignUp signup(MemberReqDTO.SignUp dto) {

        String encodedPassword = passwordEncoder.encode(dto.password());

        Member newMember = MemberConverter.toSignUp(dto, encodedPassword); // dto -> Entity

        memberRepository.save(newMember);
        // 이미 같은 회원이 존재하면 에러 반환하는 코드 필요 (현재는 구현 불가)

        return MemberResDTO.SignUp.builder()
                .memberId(newMember.getId())
                .build();

    }

    public MemberResDTO.Login login(MemberReqDTO.@Valid Login dto) {
        // 이메일로 유저 조회
        Member member = memberRepository.findByEmail(dto.email()).orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.MEMBER_INVALID_PASSWORD);
        }

        // AuthMember로 변환 후 토큰 발급
        AuthMember authMember = new AuthMember(member);
        String accessToken = jwtUtil.createAccessToken(authMember);

        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }
}
