package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.restaurant.entity.address.EupMyeonDong;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record Info(
            Long id,
            String name,
            Gender gender,
            LocalDate birth,
            String eupMyeonDong,
            List<String> preference
    ) {
    }

    public record Location(
            EupMyeonDong eupMyeonDong
    ) {
    }

    public record MissionCount(
            Integer missionCount
    ) {
    }

    @Builder
    public record MyPage(
            String nickname,
            String email,
            String phoneNumber,
            Long point
    ) {
    }
}
