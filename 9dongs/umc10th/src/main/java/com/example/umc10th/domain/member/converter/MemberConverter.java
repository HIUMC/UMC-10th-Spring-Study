package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.global.security.dto.OAuthDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberConverter {

    public static MemberResDTO.MyPageDTO toMyPageDTO(Member member, Integer totalPoint) {
        return MemberResDTO.MyPageDTO.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhonenumber())
                .point(totalPoint)
                .build();
    }

    /**
     * 폼 회원가입 (LOCAL) 용 컨버터
     */
    public static Member toMember(MemberReqDTO.SignUpDTO request, String encodedPassword) {
        return Member.builder()
                .email(request.email())
                .password(encodedPassword)
                .name(request.name())
                .nickname(request.nickname())
                .gender(request.gender())
                .birth(LocalDate.of(request.birthYear(), request.birthMonth(), request.birthDay()))
                .address(request.address() + " " + request.specAddress())
                .phonenumber(request.phoneNumber())
                // 폼 회원가입 시 소셜 로그인 필드 더미 값 주입
                .socialType(SocialType.LOCAL)
                .socialUid(request.email())
                // DTO에 없는 필수값들
                .tosAgreeStatus(true)
                .build();
    }

    /**
     * 소셜 로그인 (OAuth) 용 컨버터
     */
    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .email(dto.getSocialEmail())
                .name(dto.getName())
                .password("")
                .nickname(dto.getName())
                .gender(Gender.NONE)
                .birth(null)
                .address("NONE")
                .phonenumber("NONE")
                .tosAgreeStatus(true)
                .build();
    }

    public static MemberResDTO.SignUpResultDTO toSignUpResultDTO(Member member) {
        return MemberResDTO.SignUpResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now().toString())
                .build();
    }

    public static MemberResDTO.LoginResultDTO toLoginResultDTO(String accessToken) {
        return MemberResDTO.LoginResultDTO.builder()
                .accessToken(accessToken)
                .build();
    }

}