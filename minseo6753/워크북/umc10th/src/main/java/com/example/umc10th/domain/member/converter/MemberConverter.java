package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.restaurant.entity.address.EupMyeonDong;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.SignUp dto, EupMyeonDong eupMyeonDong) {
        return Member.builder()
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .eupMyeonDong(eupMyeonDong)
                .build();

    }

    public static MemberResDTO.Info toInfo(Member member) {
        return MemberResDTO.Info.builder()
                .id(member.getId())
                .name(member.getName())
                .gender(member.getGender())
                .birth(member.getBirth())
                .eupMyeonDong(member.getEupMyeonDong().getName())
                .preference(member.getPreference() == null ? null
                        : member.getPreference().stream().map(p -> p.getCategory().getName()).toList())
                .build();
    }

    public static MemberResDTO.MyPage toMyPage(Member member) {
        return MemberResDTO.MyPage.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }
}
