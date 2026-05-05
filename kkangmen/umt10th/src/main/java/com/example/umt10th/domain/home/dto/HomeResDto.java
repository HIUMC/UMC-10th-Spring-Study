package com.example.umt10th.domain.home.dto;

import com.example.umt10th.domain.member.enums.Address;
import lombok.Builder;
import org.hibernate.validator.constraints.BitcoinAddress;

import java.util.List;

public class HomeResDto {

    @Builder
    public record HomeResponseDto(
            CurrentRegion currentRegion,
            Integer clearedMissionCount,
            List<Mission> missionList
    ){}

    // 현재 지역
    @Builder
    public record CurrentRegion(
            Long regionId,
            String locateName
    ){}

    // 홈 미션 목록
    @Builder
    public record Mission(
            Long missionId,
            String storeName,
            String category,
            String deadline,
            Integer point
    ){}
}
