package com.example.demo.domain.member.converter;

import com.example.demo.domain.member.dto.MemberReqDTO;
import com.example.demo.domain.member.dto.MemberResDTO;
import com.example.demo.domain.member.entity.Member;

public class MemberConverter {

    // 마이 페이지
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return new MemberResDTO.GetInfo(
                member.getId(),
                member.getName(),
                member.getGender(),
                member.getBirth(),
                member.getAddress(),
                member.getMemberFoodList(),
                member.getMemberTermList()
        );


    }

    public static Member toEntity(MemberReqDTO.SignupRequest request, String encodedPassword) {
        return Member.builder()
                .name(request.name())
                .gender(request.gender())
                .birth(request.birth())
                .address(request.address())
                .detailAddress(request.detailAddress())
                .email(request.email())
                .password(encodedPassword)
                .build();
    }

    public static MemberResDTO.SignupResponse toSignupResponse(Member member) {
        return MemberResDTO.SignupResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }
}
