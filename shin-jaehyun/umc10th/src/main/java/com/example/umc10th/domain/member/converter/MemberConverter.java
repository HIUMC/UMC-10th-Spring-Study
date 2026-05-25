package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.global.dto.KakaoDTO;
import com.example.umc10th.global.dto.OAuthDTO;
import com.example.umc10th.global.entity.AuthMember;

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

    // 소셜 로그인 요청
    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .name(dto.getName())
                .email(dto.getSocialEmail())
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .build();
    }

    // 회원가입 응답
    public static MemberResDTO.SignupResult toSignupResult(Member member) {
        return MemberResDTO.SignupResult.builder()
                .id(member.getId())
                .build();
    }

    // 로그인 응답
    public static MemberResDTO.GetAccessToken toGetAccessToken(String accessToken) {
        return MemberResDTO.GetAccessToken.builder()
                .accessToken(accessToken)
                .build();
    }
}
