package com.example.umc10th.domain.member.dto;


import com.example.umc10th.domain.region.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;

import java.time.LocalDate;

public class MemberRequestDTO {

    //마이페이지

    public record GetInfo(
            Long userId
    ){}


    //회원가입
    public record Join(
            String name,
            String email,
            Gender gender,
            LocalDate birth,
            Address address,
            String phoneNumber
    ){}

}
