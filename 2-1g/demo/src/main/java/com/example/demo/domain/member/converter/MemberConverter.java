package com.example.demo.domain.member.converter;

import com.example.demo.domain.member.dto.MemberResponseDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.store.entity.Region;

public class MemberConverter {

    public static MemberResponseDTO.MeResultDTO toHomeSummaryResultDTO(Member member) {

        return MemberResponseDTO.MeResultDTO.builder()
                .memberId(member.getId())
                .point(member.getPoint())
                .email(member.getEmail())
                .number(member.getNumber())
                .build();
    }
}
