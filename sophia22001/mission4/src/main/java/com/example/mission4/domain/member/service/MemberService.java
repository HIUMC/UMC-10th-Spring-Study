package com.example.mission4.domain.member.service;

import com.example.mission4.domain.member.converter.MemberConverter;
import com.example.mission4.domain.member.dto.MemberReqDTO;
import com.example.mission4.domain.member.dto.MemberResDTO;
import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.member.exception.MemberException;
import com.example.mission4.domain.member.exception.code.MemberErrorCode;
import com.example.mission4.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        // DTO에서 유저 id 추출
        Long memberId = dto.memberId();

        // DB에서 유저 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 컨버터로 응답 DTO 생성, return
        return MemberConverter.toGetInfo(member); // Entity -> dto

    }

    public MemberResDTO.SignUp signup(MemberReqDTO.SignUp dto) {

        Member newMember = MemberConverter.toSignUp(dto); // dto -> Entity

        memberRepository.save(newMember);
        // 이미 같은 회원이 존재하면 에러 반환하는 코드 필요 (현재는 구현 불가)

        return MemberResDTO.SignUp.builder()
                .memberId(newMember.getId())
                .build();

    }
}
