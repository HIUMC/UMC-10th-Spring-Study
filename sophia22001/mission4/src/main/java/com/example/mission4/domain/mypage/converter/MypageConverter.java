package com.example.mission4.domain.mypage.converter;

import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.mypage.dto.MypageResDTO;

public class MypageConverter {
    public static MypageResDTO.GetMypage toGetMypage(Member member) {
        return MypageResDTO.GetMypage.builder()
                .username(member.getName())
                .profileUrl(member.getProfileUrl())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getMyPoint())
                .build();
    }
}
