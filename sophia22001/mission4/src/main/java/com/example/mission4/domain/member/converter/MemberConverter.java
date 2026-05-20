package com.example.mission4.domain.member.converter;

import com.example.mission4.domain.member.dto.MemberReqDTO;
import com.example.mission4.domain.member.dto.MemberResDTO;
import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.member.entity.mapping.MemberFood;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    public static Member toSignUp(MemberReqDTO.SignUp dto, String encodedPassword) {
        // 1. Member 객체 생성
        Member member = Member.builder()
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .email(dto.email())
                .password(encodedPassword)
                .memberFoodList(new ArrayList<>()) // // 여기서 리스트를 비워두고, 나중에 채움
                .build();

        // 2. DTO의 List<Food>(Enum)를 List<MemberFood>(Entity)로 변환
        List<MemberFood> memberFoodList = dto.preferFoods().stream()
                .map(food -> {
                    return MemberFood.builder()
                            .food(null) //  여기 수정 필요 !!
                            .member(member)
                            .build();
                }).toList();

        // 3. Member 객체에 생성된 리스트를 넣기
        memberFoodList.forEach(memberFood -> member.getMemberFoodList().add(memberFood));

        return member;
    }
}
