package com.example.mission4.domain.member.dto;

import lombok.Builder;


public class MemberResDTO {

    @Builder // 엔티티의 데이터를 DTO로 옮겨 담는 과정을 편하게 하기 위해 사용
    public record GetInfo( // record
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}


}
