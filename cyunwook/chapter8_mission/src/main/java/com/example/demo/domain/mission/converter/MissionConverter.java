package com.example.demo.domain.mission.converter;

import com.example.demo.domain.mission.dto.MissionReqDTO;
import com.example.demo.domain.mission.dto.MissionResDTO;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.Store;
import com.example.demo.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    //가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    //가게 내 미션 조회
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ) {
        return new MissionResDTO.GetMission(
                mission.getId(),
                mission.getPoint(),
                mission.getConditional()
        );
    }

    //페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return new MissionResDTO.Pagination<>(data, pageNumber, pageSize);
    }
    //내미션 조회
    public static MissionResDTO.MissionPageDTO toMissionPageDTO(
            Page<MemberMission> memberMissions) {

        List<MissionResDTO> missions = memberMissions.getContent().stream()
                .map(mm -> MissionResDTO.builder()
                        .missionId(mm.getMission().getId())
                        .storeName(mm.getMission().getStore().getName())
                        .conditional(mm.getMission().getConditional())
                        .point(mm.getMission().getPoint())
                        .deadline(mm.getMission().getDeadline())
                        .isComplete(mm.getIsComplete())
                        .build())
                .toList();

        return MissionResDTO.MissionPageDTO.builder()
                .missions(missions)
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .currentPage(memberMissions.getNumber())
                .build();
    }
}

