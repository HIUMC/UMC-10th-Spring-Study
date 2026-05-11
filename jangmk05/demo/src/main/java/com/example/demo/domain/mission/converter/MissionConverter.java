package com.example.demo.domain.mission.converter;

import com.example.demo.domain.mission.dto.MissionReqDTO;
import com.example.demo.domain.mission.dto.MissionResDTO;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.Store;
import com.example.demo.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MyMissionPreviewDTO toMyMissionPreviewDTO(MemberMission memberMission) {
        return MissionResDTO.MyMissionPreviewDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .reward(memberMission.getMission().getMissionPoint())
                .conditional(memberMission.getMission().getConditional())
                .status(memberMission.getMissionStatus().name())
                .build();
    }

    public static MissionResDTO.MyMissionPreviewListDTO toMyMissionPreviewListDTO(Page<MemberMission> memberMissionPage) {

        List<MissionResDTO.MyMissionPreviewDTO> missionList = memberMissionPage.stream()
                .map(MissionConverter::toMyMissionPreviewDTO)
                .toList();

        return MissionResDTO.MyMissionPreviewListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }

    public static MissionResDTO.AvailableMissionDTO toAvailableMissionDTO(Mission mission) {
        return MissionResDTO.AvailableMissionDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .reward(mission.getMissionPoint())
                .conditional(mission.getConditional())
                .build();
    }

    public static MissionResDTO.AvailableMissionListDTO toAvailableMissionListDTO(Page<Mission> missionPage) {

        List<MissionResDTO.AvailableMissionDTO> missionList = missionPage.stream()
                .map(MissionConverter::toAvailableMissionDTO)
                .toList();

        return MissionResDTO.AvailableMissionListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    // 가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .missionPoint(dto.missionPoint())
                .deadline(dto.deadline())
                .build();
    }

    // 가게 내 미션 조회
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ) {
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .missionPoint(mission.getMissionPoint())
                .missionId(mission.getId())
                .build();
    }

    // 페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}