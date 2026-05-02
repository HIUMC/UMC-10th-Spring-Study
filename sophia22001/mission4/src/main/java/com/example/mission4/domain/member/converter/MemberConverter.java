package com.example.mission4.domain.member.converter;

import com.example.mission4.domain.member.dto.MemberResDTO;
import com.example.mission4.domain.member.entity.Member;

public class MemberConverter {
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }
}
