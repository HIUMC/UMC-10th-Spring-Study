package com.example.umt10th.domain.member.converter;

import com.example.umt10th.domain.member.dto.MemberResDTO;
import com.example.umt10th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .address(member.getAddress())
                .birth(member.getBirth())
                .detailAddress(member.getDetailAddress())
                .gender(member.getGender())
                .build();
    }
}
