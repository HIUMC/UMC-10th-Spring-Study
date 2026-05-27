package com.example.umt10th.domain.member.converter;

import com.example.umt10th.domain.auth.dto.req.SignupReqDto;
import com.example.umt10th.domain.member.dto.MemberResDTO;
import com.example.umt10th.domain.member.entity.Member;
import com.example.umt10th.global.security.dto.OAuthDTO;

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

    public static Member createMember(SignupReqDto.Signup dto, String encodedPw){
        return Member.builder()
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .email(dto.email())
                .password(encodedPw)
                .build();
    }

    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .name(dto.getName())
                .email(dto.getSocialEmail())
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .build();
    }
}
