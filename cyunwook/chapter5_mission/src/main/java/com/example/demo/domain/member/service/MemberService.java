package com.example.demo.domain.member.service;

import com.example.demo.domain.member.dto.MemberReqDTO;
import com.example.demo.domain.member.dto.MemberResDTO;
import com.example.demo.domain.member.converter.MemberConverter;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository; // (Repository도 임포트 안되어있다면 Alt+Enter로 추가!)
import com.example.demo.domain.member.exception.MemberException; // (Exception도 임포트 추가!)
import com.example.demo.domain.member.exception.code.MemberErrorCode; // (ErrorCode도 임포트 추가!)
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {

        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));


        return MemberConverter.toGetInfo(member);
    }
}