package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberAddressRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberAddressRepository memberAddressRepository;
    private final PasswordEncoder passwordEncoder;

    // 홈 화면 - 내 포인트, 미션 진행률 조회
    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Member member = memberRepository.findById(dto.id())
                .orElseThrow(() -> new ProjectException(MemberErrorCode.NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    // 마이페이지 조회
    public MemberResDTO.GetMyPage getMyPage(MemberReqDTO.GetInfo dto) {
        Member member = memberRepository.findById(dto.id())
                .orElseThrow(() -> new ProjectException(MemberErrorCode.NOT_FOUND));

        return MemberConverter.toGetMyPage(member);
    }

    // 회원가입
    @Transactional
    public void signUp(MemberReqDTO.SignUp dto) {
        String encodedPassword = passwordEncoder.encode(dto.password());
        Member savedMember = memberRepository.save(MemberConverter.toMember(dto, encodedPassword));
        memberAddressRepository.save(MemberConverter.toMemberAddress(dto, savedMember));
    }
}
