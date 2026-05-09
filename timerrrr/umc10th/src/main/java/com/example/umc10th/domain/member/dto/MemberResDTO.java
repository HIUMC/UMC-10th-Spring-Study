package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    //마이페이지
    @Builder
    public record GetInfo(
            String name,
            String email,
            String phoneNumber,
            Integer point
    ){}
}
