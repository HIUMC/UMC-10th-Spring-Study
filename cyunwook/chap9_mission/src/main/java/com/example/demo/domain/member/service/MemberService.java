package com.example.demo.domain.member.service;

import com.example.demo.domain.member.converter.MemberConverter;
import com.example.demo.domain.member.dto.MemberReqDTO;
import com.example.demo.domain.member.dto.MemberResDTO;
import com.example.demo.domain.member.dto.MyPageResponseDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.enums.Address;
import com.example.demo.domain.member.exception.MemberException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.review.repository.ReviewRepository;
import com.example.demo.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    public MyPageResponseDTO getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Long reviewCount = reviewRepository.countByMemberId(memberId);

        return MyPageResponseDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .reviewCount(reviewCount)
                .build();
    }

    @Transactional
    public Member createMember(MemberReqDTO.CreateMember request) {
        Member member = Member.builder()
                .name(request.getName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .address(request.getAddress())
                .detailedAddress(request.getDetailedAddress())
                .socialUid(request.getSocialUid())
                .socialType(request.getSocialType())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .point(0)
                .build();
        return memberRepository.save(member);
    }

    // 마이페이지
    public MemberResDTO.GetInfo getInfo(AuthMember member) {
        // 컨버터를 이용해서 응답 DTO 생성 & return
        return MemberConverter.toGetInfo(member.getMember());
    }
}