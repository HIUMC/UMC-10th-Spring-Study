package com.example.umt10th.domain.home.service;

import com.example.umt10th.domain.home.dto.HomeResDto;
import com.example.umt10th.domain.member.enums.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    public HomeResDto.HomeResponseDto getHomePage() {

        HomeResDto.CurrentRegion region = HomeResDto.CurrentRegion.builder()
                .regionId(10L)
                .regionName(Address.강남구)
                .build();

        HomeResDto.Mission mission1 = HomeResDto.Mission.builder()
                .missionId(1L)
                .storeName("써브웨이")
                .category("중식당")
                .deadline("D-7")
                .point(1000)
                .build();

        HomeResDto.Mission mission2 = HomeResDto.Mission.builder()
                .missionId(1L)
                .storeName("KFC")
                .category("일식당")
                .deadline("D-10")
                .point(2000)
                .build();

        return new HomeResDto.HomeResponseDto(region, 10, List.of(mission1, mission2));
    }
}
