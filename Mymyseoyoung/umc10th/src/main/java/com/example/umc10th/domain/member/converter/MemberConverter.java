package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;

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

    public static Member toMember(MemberRequestDTO.Join request) {
        return Member.builder()
                .email(request.email())// 주의: 실제 구현 시에는 PasswordEncoder로 암호화 필요
                .name(request.name())
                .gender(request.gender())
                .birth(request.birth())
                .address(request.address())
                .phoneNumber(request.phoneNumber())
                .point(0) // 초기 포인트는 0으로 설정
                .build();
    }

    public static MemberResponseDTO.JoinResult toJoinResult(Member member) {
        return MemberResponseDTO.JoinResult.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }
    public static MemberResponseDTO.GetMyPointInfo toGetMyPointInfo(Member member) {
        return MemberResponseDTO.GetMyPointInfo.builder()
                .memberId(member.getId())
                .name(member.getName())
                .point(member.getPoint())
                .build();
    }
}
