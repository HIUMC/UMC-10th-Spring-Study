package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.foodpreference.service.FoodPreferenceService;
import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.MemberErrorCode;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.memberterm.service.MemberTermService;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodPreferenceService foodPreferenceService;
    private final MemberTermService memberTermService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public AuthResDTO.SignUp signUp(AuthReqDTO.SignUp dto) {
        String encodedPassword = passwordEncoder.encode(dto.password());

        if (memberRepository.findByEmail(dto.email()).isPresent()) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        Member member = MemberConverter.toMember(dto, encodedPassword);
        memberRepository.save(member);

        foodPreferenceService.saveFoodPreferences(member, dto.foodPreferences());
        memberTermService.saveTermAgreements(member, dto.termAgreements());

        return MemberConverter.toSignUp(member);
    }

    public MemberResDTO.GetInfo getInfo(AuthMember member) {

        // 컨버터를 이용해서 응답 DTO 생성 & return
        return MemberConverter.toGetInfo(member.getMember());
    }

    public MemberResDTO.GetPoint getPoint(AuthMember member) {

        return MemberConverter.toGetPoint(member.getMember());
    }

    @Transactional
    public MemberResDTO.UpdateInfo updateInfo(AuthMember authMember) {
        Member member = authMember.getMember();

        if (member.getNickname() != null) member.setNickname(member.getNickname());
        if (member.getProfileUrl() != null) member.setProfileUrl(member.getProfileUrl());
        if (member.getPhoneNumber() != null) member.setPhoneNumber(member.getPhoneNumber());

        memberRepository.save(member);

        return MemberConverter.toUpdateInfo(member);
    }

    public AuthResDTO.Login login(AuthReqDTO.Login dto) {
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.LOGIN_FAILED));

        if(!passwordEncoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.LOGIN_FAILED);
        }

        AuthMember authMember = new AuthMember(member);
        String accessToken = jwtUtil.createAccessToken(authMember);

        return MemberConverter.toLogin(accessToken);
    }
}
