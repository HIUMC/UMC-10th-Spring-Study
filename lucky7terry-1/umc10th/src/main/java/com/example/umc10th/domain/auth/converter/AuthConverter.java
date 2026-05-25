package com.example.umc10th.domain.auth.converter;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Provider;

public class AuthConverter {

    public static Member createMember(AuthReqDTO.SignupDTO dto, String encodedPassword) {
        return Member.builder()
                .name(dto.name())
                .nickname(dto.name()) // 임시로 name 사용
                .gender(dto.gender())
                .birthDate(dto.birthDate())
                .address(dto.address())
                .email(dto.email())
                .password(encodedPassword)
                .locationAllow(dto.agree().location())
                .marketingAllow(dto.agree().marketing())
                .socialProvider(Provider.LOCAL)
                .socialUid("0")
                .point(0)
                .build();
    }
}
