package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.MyPageDTO toMyPageDTO(Member member) {
        return MemberResDTO.MyPageDTO.builder()
                .nickname(member.getNickname())
                .point(member.getPoint())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    public static Member createMember(MemberReqDTO.SignupDTO dto) {
        return Member.builder()
                .name(dto.name())
                .nickname(dto.nickname())
                .gender(dto.gender())
                .birthDate(dto.birthDate())
                .address(dto.address())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .build();
    }
}

