package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.MemberResponseDTO;
import com.example.umc10th.domain.user.entity.Member;

public class MemberConverter {

    // 마이페이지 응답 DTO 변환
    public static MemberResponseDTO.GetInfo toGetInfo(Member user) {
        return MemberResponseDTO.GetInfo.builder()
                .email(user.getEmail())
                .name(user.getName())
                .point(user.getPoint())
                .phoneNumber(user.getPhoneNumber())
                .profileUrl(user.getProfileUrl())
                .build();
    }
}
