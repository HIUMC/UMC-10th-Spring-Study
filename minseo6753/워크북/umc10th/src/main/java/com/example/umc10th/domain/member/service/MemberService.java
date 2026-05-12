package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResDTO.Info signup(MemberReqDTO.SignUp request) {
        return null;
    }

    public MemberResDTO.Location getLocation() {
        return null;
    }

    public MemberResDTO.Location updateLocation(MemberReqDTO.Location request) {
        return null;
    }

    public MemberResDTO.MissionCount getMissionCount() {
        return null;
    }

    public MemberResDTO.MyPage myPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        return MemberConverter.toMyPage(member);
    }
}
