package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.enums.Gender;
import com.example.demo.domain.member.enums.Preference;
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
            String address,
            List<Preference> preferences,
            boolean agreement
    ){}
}
