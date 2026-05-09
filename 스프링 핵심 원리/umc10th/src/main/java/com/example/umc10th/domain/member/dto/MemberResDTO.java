package com.example.umc10th.domain.member.dto;

import lombok.*;

public class MemberResDTO {

    @Builder @Getter @NoArgsConstructor @AllArgsConstructor
    public static class MyPageDTO {
        private String name;
        private String email;
        private String phoneNumber;
        private Integer point;
    }
}