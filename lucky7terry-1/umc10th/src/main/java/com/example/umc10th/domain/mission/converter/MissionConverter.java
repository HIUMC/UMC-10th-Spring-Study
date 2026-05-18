package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;

import java.util.List;

public class MissionConverter {

    // 가게 미션 생성
    public static Mission toMission(Store store, MissionReqDTO.CreateMission dto){
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    // 가게 내 미션 & 현재 진행 중인 미션 조회
    public static MissionResDTO.GetMission toGetMission(Mission mission){
        return MissionResDTO.GetMission.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .conditional(mission.getConditional())
                .build();
    }

    // 페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }

//    public static MissionResDTO.MissionListDTO toMissionListDTO(
//            List<MemberMission> memberMissions,
//            Boolean hasNext,
//            Long nextCursor
//    ) {
//        List<MissionResDTO.MissionPreviewDTO> missionList = memberMissions.stream()
//                .map(MissionConverter::toMissionPreviewDTO)
//                .toList();
//
//        return MissionResDTO.MissionListDTO.builder()
//                .missionList(missionList)
//                .hasNext(hasNext)
//                .nextCursor(nextCursor)
//                .build();
//    }
//
//    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(MemberMission memberMission) {
//        boolean completed = Boolean.TRUE.equals(memberMission.getIsCompleted());
//
//        return MissionResDTO.MissionPreviewDTO.builder()
//                .memberMissionId(memberMission.getId())
//                .missionId(memberMission.getMission().getId())
//                .storeName(memberMission.getMission().getStore().getStoreName())
//                .point(memberMission.getMission().getPoint())
//                .status(completed ? "진행 완료" : "진행 중")
//                .content(memberMission.getContent())
//                .deadline(memberMission.getMission().getDeadline())
//                .build();
//    }
//
//    public static MissionResDTO.MissionCompleteDTO toMissionCompleteDTO(MemberMission memberMission) {
//        return MissionResDTO.MissionCompleteDTO.builder()
//                .missionId(memberMission.getMission().getId())
//                .isCompleted(memberMission.getIsCompleted())
//                .build();
//    }
}

