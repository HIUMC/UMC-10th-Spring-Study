package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.dto.MemberResDTO;

public class MemberConverter {

    public static MemberResDTO.MyPageDTO toMyPageDTO(Member member, Integer totalPoint) {
        return MemberResDTO.MyPageDTO.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhonenumber())
                .point(totalPoint)
                .build();
    }
}