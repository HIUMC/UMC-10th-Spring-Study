package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    // 마이페이지
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    // 회원가입 요청
    public static Member toMember(MemberReqDTO.Signup dto, String encodedPassword) {
        return Member.builder()
                .email(dto.email())
                .password(encodedPassword)
                .name(dto.name())
                .birthDate(dto.birthDate())
                .gender(dto.gender())
                .address(dto.address())
                .phoneNumber(dto.phoneNumber())
                .point(0L)
                .build();
    }

    // 회원가입 응답
    public static MemberResDTO.SignupResult toSignupResult(Member member) {
        return MemberResDTO.SignupResult.builder()
                .id(member.getId())
                .build();
    }
}
