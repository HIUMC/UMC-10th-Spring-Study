package com.example.umc10th.domain.auth.service;

import com.example.umc10th.domain.auth.converter.AuthConverter;
import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResDTO.SignupDTO saveMember(@Valid AuthReqDTO.SignupDTO dto) {

        String encodedPassword = passwordEncoder.encode(dto.password());

        Member member = AuthConverter.createMember(dto, encodedPassword);

        Member savedMember = memberRepository.save(member);

        return AuthResDTO.SignupDTO.builder()
                .memberId(savedMember.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public String  login(AuthReqDTO.LoginDTO dto) {
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() ->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        if(!passwordEncoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.UNAUTHORIZED);
        }

        return jwtUtil.createAccessToken(new AuthMember(member));


    }
}
