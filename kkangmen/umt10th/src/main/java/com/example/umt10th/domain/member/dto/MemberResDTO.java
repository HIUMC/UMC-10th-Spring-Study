package com.example.umt10th.domain.member.dto;

import com.example.umt10th.domain.member.enums.Address;
import com.example.umt10th.domain.member.enums.Gender;
import lombok.Builder;

import java.time.LocalDate;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String detailAddress,
            Integer point,
            String email,
            String phoneNumber
    ){}
}
