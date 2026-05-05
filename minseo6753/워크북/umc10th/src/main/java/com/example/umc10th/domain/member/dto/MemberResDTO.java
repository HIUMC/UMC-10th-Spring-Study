package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.restaurant.entity.address.EupMyeonDong;
import java.time.LocalDate;
import java.util.List;

public class MemberResDTO {

    public record Info(
            String name,
            Gender gender,
            LocalDate birth,
            String siDo,
            String siGunGu,
            String eupMyeonDong,
            List<String> preference,
            Boolean agreeLocation,
            Boolean agreeMarketing
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
}
