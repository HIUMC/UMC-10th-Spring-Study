package com.example.demo.domain.member.converter;

import com.example.demo.domain.member.dto.MemberRequestDTO;
import com.example.demo.domain.member.dto.MemberResponseDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.enums.SocialType;
import com.example.demo.domain.store.entity.Region;
import jakarta.persistence.Converter;

public class MemberConverter {

    public static MemberResponseDTO.MeResultDTO toHomeSummaryResultDTO(Member member) {

        return MemberResponseDTO.MeResultDTO.builder()
                .memberId(member.getId())
                .point(member.getPoint())
                .email(member.getEmail())
                .number(member.getNumber())
                .build();
    }

    public static MemberResponseDTO.HomeSummaryResultDTO toHomeSummaryResultDTO(Member member, Region region) {

        return MemberResponseDTO.HomeSummaryResultDTO.builder()
                .regionId(region.getId())
                .regionName(region.getName())
                .point(member.getPoint())
                .completionCount(member.getCompletionCount())
                .build();
    }

    public static Member toMember(MemberRequestDTO.SignUpRequest request, String encodedPassword) {
        return Member.builder()
                .name(request.getName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .address(request.getAddress())
                .agreement(request.getAgreement())
                .email(request.getEmail())
                .number(request.getNumber())
                .nickname(request.getNickname())
                .password(encodedPassword)
                .point(0L)
                .completionCount(0L)
                .socialLogin(SocialType.NONE)
                .build();
    }
}
