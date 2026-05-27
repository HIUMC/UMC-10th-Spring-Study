package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.global.security.dto.OAuthDTO;

import java.time.LocalDate;

public class MemberConverter {

    public static MemberResDTO.MyPageDTO toMyPageDTO(Member member) {
        return MemberResDTO.MyPageDTO.builder()
                .nickname(member.getNickname())
                .point(member.getPoint())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .email(dto.getSocialEmail())
                .name(dto.getName())
                .nickname(dto.getName())
                .password(null)
                // OAuth 회원가입 시 임시 기본값
                .password("OAUTH_USER")
                .gender(Gender.FEMALE)
                .address(Address.마포구)
                .detailAddress("미입력")
                .birthDate(LocalDate.of(2000, 1, 1))
                .phoneNumber("미입력")
                .point(0)
                .locationAllow(false)
                .marketingAllow(false)
                .build();
    }

    public static MemberResDTO.Login toLogin(String accessToken) {
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }
}

