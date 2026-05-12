package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionDTO toMission(Mission mission) {
        return MissionResDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .content(mission.getContent())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .build();
    }


    public static MissionResDTO.MissionListDTO toMissionList(List<Mission> missionList) {

        List<MissionResDTO.MissionDTO> missionDTOList = missionList.stream()
                .map(MissionConverter::toMission)
                .toList();


        return MissionResDTO.MissionListDTO.builder()
                .missionList(missionDTOList)
                .lastMissionId(missionDTOList.isEmpty() ? null : missionDTOList.get(missionDTOList.size() - 1).missionId())
                .hasNext(false)
                .build();
    }


    //가게 미션 생성
    public static Mission tMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .deadline(dto.deadline())
                .content("테스트")
                .reward(3L)
                .build();
    }

    //가게 내 미션 죠회
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ) {
        return MissionResDTO.GetMission.builder()
                .missionId(mission.getId())
                .build();
    }
    // 페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data, Integer pageSize, Integer pageNumber
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }


    public static MissionResDTO.GetMission getProgressMissions(
            MemberMission memberMissionPage){


        return MissionResDTO.GetMission.builder()
                .missionId(memberMissionPage.getId())
                .build();
    }
}
