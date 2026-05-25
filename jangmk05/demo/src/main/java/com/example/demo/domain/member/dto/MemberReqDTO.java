package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.entity.Term;
import com.example.demo.domain.member.entity.mapping.MemberFood;
import com.example.demo.domain.member.entity.mapping.MemberTerm;
import com.example.demo.domain.member.enums.FoodName;
import com.example.demo.domain.member.enums.Gender;
import com.example.demo.domain.mission.enums.Address;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    // 마이 페이지
    public record GetInfo(
            Long id
    ) {}

    public record AgreeRequest(
            Boolean age,
            Boolean service,
            Boolean privacy,
            Boolean location,
            Boolean marketing
    ) {}

    public record SignupRequest(
            AgreeRequest agree,
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String detailAddress,
            List<FoodName> foodList,
            String email,
            String password
    ) {}

    public record LoginRequest(
            String email,
            String password
    ) {}

}
