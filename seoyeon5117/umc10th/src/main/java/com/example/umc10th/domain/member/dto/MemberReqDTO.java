package com.example.umc10th.domain.member.dto;

public class MemberReqDTO {

    // 마이페이지
    public record GetInfo(Long id) {
    }

    public record UpdateInfo(
            String name,
            String profileUrl,
            String phoneNumber
    ) {
    }

    public record GetPoint(Long id) {
    }
}
