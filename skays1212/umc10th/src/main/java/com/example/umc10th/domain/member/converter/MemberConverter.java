package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.global.security.dto.OAuthDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.SignupReqDTO request, String encodedPassword) {
        return Member.builder()
                .email(request.getUserId())
                .password(encodedPassword)
                .name(request.getName())
                .nickname(request.getNickname())
                .gender(Gender.valueOf(request.getGender().toUpperCase()))
                .birth(LocalDate.parse(request.getBirthDate(), DateTimeFormatter.ISO_LOCAL_DATE))
                .address(Address.valueOf(request.getAddress()))
                .detailAddress("")
                .socialUid("")
                .socialType(SocialType.LOCAL)
                .profile_url("")
                .build();
    }

    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .email(dto.getSocialEmail())
                .password("")
                .name(dto.getName())
                .nickname(dto.getName())
                .gender(Gender.NONE)
                .birth(LocalDate.of(1900, 1, 1))
                .address(Address.강남구)
                .detailAddress("")
                .socialUid(dto.getSocialUid())
                .socialType(dto.getSocialType())
                .profile_url("")
                .build();
    }

    public static MemberResDTO.SignupResDTO toSignupResDTO(Member member) {
        return MemberResDTO.SignupResDTO.builder()
                .memberId(member.getId())
                .userId(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }

    public static MemberResDTO.Login toLogin(String accessToken) {
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }

    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .memberId(member.getId())
                .userId(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .gender(member.getGender().name())
                .birthDate(member.getBirth() != null ? member.getBirth().toString() : null)
                .address(member.getAddress() != null ? member.getAddress().name() : null)
                .build();
    }
}
