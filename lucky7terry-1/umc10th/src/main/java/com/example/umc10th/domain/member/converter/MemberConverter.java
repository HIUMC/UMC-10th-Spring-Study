package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.MyPageDTO toMyPageDTO(Member member) {
        return MemberResDTO.MyPageDTO.builder()
                .name(member.getName())
                .gender(member.getGender())
                .birth(member.getBirthDate())
                .address(member.getAddress())
                .detailAddress(member.getDetailAddress())
                .point(member.getPoint())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}

