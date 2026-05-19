package com.example.demo.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyPageResponseDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private Integer point;
    private Long reviewCount;
}
