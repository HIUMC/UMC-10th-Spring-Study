package com.example.mission4.domain.mypage.dto;

import lombok.Builder;

public class MypageResDTO {

    @Builder
    public record GetMypage(
            String username,
            String email,
            String phoneNumber,
            Long point

    ){}
}
