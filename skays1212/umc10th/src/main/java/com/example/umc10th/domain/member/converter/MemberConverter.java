package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.MyInfoResDTO toMyInfoResDTO(Member member) {
        return MemberResDTO.MyInfoResDTO.builder()
                .memberId(member.getId())
                .userId(member.getEmail())
                .name(member.getName())
                .nickname(member.getName())
                .gender(member.getGender().name())
                .birthDate(member.getBirth() != null ? member.getBirth().toString() : null)
                .address(member.getAddress() != null ? member.getAddress().name() : null)
                .build();
    }
}
