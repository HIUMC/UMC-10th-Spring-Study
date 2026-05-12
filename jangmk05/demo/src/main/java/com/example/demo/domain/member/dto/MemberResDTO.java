package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.entity.mapping.MemberFood;
import com.example.demo.domain.member.entity.mapping.MemberTerm;
import com.example.demo.domain.member.enums.Gender;
import com.example.demo.domain.member.enums.FoodName;
import com.example.demo.domain.mission.enums.Address;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            Long id,
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            List<MemberFood> memberFoodList,
            List<MemberTerm> memberTermList
    ){}
}
