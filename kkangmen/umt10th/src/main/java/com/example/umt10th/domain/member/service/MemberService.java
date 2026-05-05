package com.example.umt10th.domain.member.service;

import com.example.umt10th.domain.member.converter.MemberConverter;
import com.example.umt10th.domain.member.dto.MemberReqDTO;
import com.example.umt10th.domain.member.dto.MemberResDTO;
import com.example.umt10th.domain.member.entity.Member;
import com.example.umt10th.domain.member.exception.MemberException;
import com.example.umt10th.domain.member.exception.code.MemberErrorCode;
import com.example.umt10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public  MemberResDTO.saveSuccessMember saveMember(MemberReqDTO.saveMember dto){

        memberRepository.save(MemberConverter.createMember(dto));

        return MemberResDTO.saveSuccessMember.builder()
                .createdAt(LocalDateTime.now())
                .build();
    }

    public MemberResDTO.GetInfo getInfo() {

        // Authenticatio에서 memberId 추출
        Long memberId = 1L;
        // DB에서 해당 유저 ID로 데이터 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 컨버터를 이용해서 응답 DTO 생성 & return
        return MemberConverter.toGetInfo(member);
    }
}
