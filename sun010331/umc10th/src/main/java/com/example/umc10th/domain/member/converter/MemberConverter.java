package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {


    public static MemberResDTO.GetInfo toGetInfo(Member member){
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .name(member.getName())
                .build();
    }

    public static MemberResDTO.JoinDTO joinDTO(Member member){
        return MemberResDTO.JoinDTO.builder()
                .email(member.getEmail())
                .memberId(member.getId())
                .build();
    }

}
