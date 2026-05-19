package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.foodpreference.service.FoodPreferenceService;
import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.MemberErrorCode;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.memberterm.service.MemberTermService;
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

    @Transactional
    public MemberResDTO.SignUpRes signUp(MemberReqDTO.SignUp dto) {
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

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        //DTO에서 유저 ID를 추출
        Long memberId = dto.id();
        //DB에서 해당 유저 ID로 데이터 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        // 컨버터를 이용해서 응답 DTO 생성 & return
        return MemberConverter.toGetInfo(member);
    }

    public MemberResDTO.GetPoint getPoint(MemberReqDTO.GetPoint dto) {
        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetPoint(member);
    }

    @Transactional
    public MemberResDTO.UpdateInfo updateInfo(MemberReqDTO.UpdateInfo dto) {
        Member member = memberRepository.findById(dto.id())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (dto.nickname() != null) member.setNickname(dto.nickname());
        if (dto.profileUrl() != null) member.setProfileUrl(dto.profileUrl());
        if (dto.phoneNumber() != null) member.setPhoneNumber(dto.phoneNumber());

        memberRepository.save(member);

        return MemberConverter.toUpdateInfo(member);
    }
}
