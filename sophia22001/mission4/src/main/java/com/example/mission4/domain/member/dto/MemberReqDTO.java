package com.example.mission4.domain.member.dto;


import com.example.mission4.domain.member.enums.Food;
import com.example.mission4.domain.member.enums.Gender;
import com.example.mission4.domain.mission.enums.Address;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // 유저 조회
    public record GetInfo( // record
            Long memberId
    ){}

    // 유저 회원가입
    public record SignUp(
        String name,
        Gender gender,
        LocalDate birth,
        Address address,
        List<Food> preferFoods
    ){}
}
