package com.example.umt10th.domain.mission.service;

import com.example.umt10th.domain.mission.dto.MissionResDTO;
import com.example.umt10th.domain.mission.entity.Mission;
import com.example.umt10th.domain.mission.enums.Status;
import com.example.umt10th.domain.mission.exception.MissionException;
import com.example.umt10th.domain.mission.exception.code.MissionErrorCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MissionService {

    /***
     * 미션 목록 조회
     * @param status1 "completed"
     * @param status2 "in_progress"
     * @return
     */
    public MissionResDTO.MissionListDto getMissionList(String status1, String status2, String role) {

        if (role.equals("x")){
            throw new MissionException(MissionErrorCode.MISSION_FORBIDDEN);
        }

        MissionResDTO.MissionDetailDto completedMission = MissionResDTO.MissionDetailDto.builder()
                .missionId(1L)
                .storeName("써브웨이")
                .point(1000)
                .status(Status.COMPLETED)
                .deadline(null)
                .completedAt(LocalDateTime.now())
                .build();

        MissionResDTO.MissionDetailDto inProgressMission= MissionResDTO.MissionDetailDto.builder()
                .missionId(2L)
                .storeName("KFC")
                .point(2000)
                .status(Status.IN_PROGRESS)
                .deadline("2026-06-10")
                .completedAt(null)
                .build();

        return new MissionResDTO.MissionListDto(List.of(completedMission, inProgressMission));
    }

    /***
     * 미션 성공 누르기
     * @param missionId
     * @return
     */
    public MissionResDTO.MissionSuccessDto succeedMission(Long missionId) {

        // DB에서 해당 미션 Status를 완료로 바꿔준다.
        return new MissionResDTO.MissionSuccessDto(LocalDateTime.now());
    }
}
