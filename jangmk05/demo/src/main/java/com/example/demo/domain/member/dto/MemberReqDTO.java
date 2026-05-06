package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.entity.Food;
import com.example.demo.domain.member.entity.mapping.MemberFood;
import com.example.demo.domain.member.entity.mapping.MemberTerm;
import com.example.demo.domain.member.enums.Gender;
import com.example.demo.domain.member.enums.FoodName;
import com.example.demo.domain.mission.enums.Address;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    // 마이 페이지
    public record GetInfo(
            Long id
    ) {}

    public record SignupRequest(
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            List<MemberFood> memberFoodList,
            List<MemberTerm> memberTermList
    ){}


}
