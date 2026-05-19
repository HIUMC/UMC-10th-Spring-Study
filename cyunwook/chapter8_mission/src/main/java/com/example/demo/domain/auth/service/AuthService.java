package com.example.demo.domain.auth.service;

import com.example.demo.domain.auth.dto.AuthReqDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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
}