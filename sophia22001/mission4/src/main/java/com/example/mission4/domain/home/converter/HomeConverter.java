package com.example.mission4.domain.home.converter;

import com.example.mission4.domain.home.dto.HomeResDTO;
import com.example.mission4.domain.member.dto.MemberResDTO;
import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.mission.entity.mapping.MemberMission;

import java.util.List;

public class HomeConverter {

    public static HomeResDTO.GetHome toGetHome(Member member, Integer completedMissionsCount, Integer totalMissionsCount, List<MemberMission> missions) {

        List<HomeResDTO.HomeMissionDTO> notCompletedMissions = missions.stream()
                .map(memberMission -> {
                    var mission = memberMission.getMission();
                    var store = mission.getStore();

                    return HomeResDTO.HomeMissionDTO.builder()
                            .storeName(store.getName())
                            .storeCategory(store.getStoreCategory())

                            .condition(mission.getCondition())
                            .point(mission.getPoint())
                            .deadline(mission.getDeadline())
                            .build();
                }).toList();

        return HomeResDTO.GetHome.builder()
                .location(member.getDetailAddress())
                .completedMissionsCount(completedMissionsCount)
                .totalMissionsCount(totalMissionsCount)
                .achieving10MissionsPoint(1000L)
                .notCompletedMissions(notCompletedMissions)
                .build();
    }
}
