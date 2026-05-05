package com.example.umt10th.domain.member.dto;

import com.example.umt10th.domain.member.enums.Address;
import com.example.umt10th.domain.member.enums.Gender;
import com.example.umt10th.domain.member.enums.SocialType;

import java.time.LocalDate;

public class MemberReqDTO {

    // 회원가입
    public record saveMember(
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String detailAddress,
            String socialUuid,
            SocialType socialType,
            Integer point,
            String email,
            String phoneNumber
    ){}
}
