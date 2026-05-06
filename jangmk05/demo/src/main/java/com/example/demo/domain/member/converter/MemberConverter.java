package com.example.demo.domain.member.converter;

import com.example.demo.domain.member.dto.MemberReqDTO;
import com.example.demo.domain.member.dto.MemberResDTO;
import com.example.demo.domain.member.entity.Member;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemberConverter {

    // 마이 페이지
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return new MemberResDTO.GetInfo(
                member.getId(),
                member.getName(),
                member.getGender(),
                member.getBirth(),
                member.getAddress(),
                member.getMemberFoodList(),
                member.getMemberTermList()
        );


    }

    public static Member toEntity(MemberReqDTO.SignupRequest req) {
        return Member.builder()
                .name(req.name())
                .gender(req.gender())
                .birth(req.birth())
                .address(req.address())
                .memberFoodList(req.memberFoodList())
                .memberTermList(req.memberTermList())
                .build();

    }
}
