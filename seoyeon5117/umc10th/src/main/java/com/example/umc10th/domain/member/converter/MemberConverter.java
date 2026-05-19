package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Gender;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.SignUp dto, String encodedPassword) {
        return Member.builder()
                .email(dto.email())
                .password(encodedPassword)
                .nickname(dto.nickname())
                .phoneNumber(dto.phoneNumber())
                .gender(dto.gender() != null ? dto.gender() : Gender.NOT_SPECIFIED)
                .birthday(dto.birthday())
                .address(dto.address())
                .profileUrl(dto.profileUrl())
                .build();
    }

    public static MemberResDTO.SignUpRes toSignUp(Member member) {
        return MemberResDTO.SignUpRes.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
    }

    // 마이페이지
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }


    public static MemberResDTO.UpdateInfo toUpdateInfo(Member member) {
        return MemberResDTO.UpdateInfo.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    // 내 포인트
    public static MemberResDTO.GetPoint toGetPoint(Member member) {
        return MemberResDTO.GetPoint.builder()
                .point(member.getPoint())
                .build();
    }
}
