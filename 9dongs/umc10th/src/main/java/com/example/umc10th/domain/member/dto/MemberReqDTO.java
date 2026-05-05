package com.example.umc10th.domain.member.dto;

import java.util.List;

public class MemberReqDTO {
    public record SignUpDTO(
            String email,
            String password,
            String name,
            String nickname,
            String birth,
            String phoneNumber,
            List<Long> preferenceIds
    ) {
    }

    public record UpdateRegionDTO(
            Long regionId
    ) {
    }
}
