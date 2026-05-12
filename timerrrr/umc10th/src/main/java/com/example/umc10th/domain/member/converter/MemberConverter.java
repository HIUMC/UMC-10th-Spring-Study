package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.MemberAddress;

public class MemberConverter {

    // 홈 화면 상단 - Member -> MemberResDTO.GetInfo
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .point(member.getPoint())
                .currentMissionCount(member.getCurrentMissionCount())
                .build();
    }

    // 마이페이지 - Member -> MemberResDTO.GetMyPage
    public static MemberResDTO.GetMyPage toGetMyPage(Member member) {
        return MemberResDTO.GetMyPage.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber() != null ? member.getPhoneNumber() : "미인증")
                .build();
    }

    // 회원가입 - MemberReqDTO.SignUp -> Member
    public static Member toMember(MemberReqDTO.SignUp dto) {
        return Member.builder()
                .name(dto.name())
                .gender(dto.gender())
                .birthDate(dto.birthDate())
                .nickname(dto.nickname())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .build();
    }

    // 회원가입 - MemberReqDTO.SignUp -> MemberAddress
    public static MemberAddress toMemberAddress(MemberReqDTO.SignUp dto, Member member) {
        return MemberAddress.builder()
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .townName(dto.townName())
                .member(member)
                .build();
    }
}
