package com.example.umt10th.domain.mission.service;

import com.example.umt10th.domain.mission.dto.MissionResDTO;
import com.example.umt10th.domain.mission.entity.Mission;
import com.example.umt10th.domain.mission.enums.Status;
import com.example.umt10th.domain.mission.exception.MissionException;
import com.example.umt10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umt10th.domain.mission.repository.MemberMissionRepository;
import com.example.umt10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Role;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    /***
     * 미션 완료 목록 조회
     * @param isCompleted
     * @param role
     * @return
     */
    public MissionResDTO.MissionListDto getMissionList(Boolean isCompleted, String role, Long cursor) {

        if (role.equals("x")) {
            throw new MissionException(MissionErrorCode.MISSION_FORBIDDEN);
        }

        Pageable pageable = PageRequest.of(0, 10); // 0번째 페이지에서 LIMIT 10
        List<Mission> completedMissionList = memberMissionRepository.findCompletedMissionByMemberId(1L, isCompleted, cursor, pageable);

        List<MissionResDTO.MissionDetailDto> missions = new ArrayList<>();
        for (Mission mission : completedMissionList) {
            MissionResDTO.MissionDetailDto findMission = MissionResDTO.MissionDetailDto.builder()
                    .missionId(mission.getId())
                    .storeName(mission.getStore().getStoreName())
                    .point(mission.getPoint())
                    .deadline(mission.getDeadline())
                    .conditional(mission.getConditional())
                    .build();

            missions.add(findMission);
        }

        return MissionResDTO.MissionListDto.builder()
                .missionList(missions)
                .build();
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
