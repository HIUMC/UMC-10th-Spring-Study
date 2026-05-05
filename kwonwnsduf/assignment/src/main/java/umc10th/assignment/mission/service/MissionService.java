package umc10th.assignment.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc10th.assignment.mission.converter.MissionConverter;
import umc10th.assignment.mission.dto.MissionResponseDto;
import umc10th.assignment.mission.entity.Mission;
import umc10th.assignment.mission.repository.MissionRepository;
import umc10th.assignment.usermission.entity.MissionStatus;
import umc10th.assignment.usermission.entity.UserMission;
import umc10th.assignment.usermission.repository.UserMissionRepository;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    public MissionResponseDto.MissionPreviewListDTO getMissionsByRegion(
            Long regionId,
            Pageable pageable
    ) {
        Page<Mission> missionPage =
                missionRepository.findAvailableMissionsByRegion(regionId, pageable);

        Page<MissionResponseDto.MissionPreviewDTO> result =
                missionPage.map(MissionConverter::toMissionPreviewDTO);

        return MissionConverter.toMissionPreviewListDTO(result);
    }

    public MissionResponseDto.MissionPreviewListDTO getMyMissions(
            Long memberId,
            MissionStatus status,
            Pageable pageable
    ) {
        Page<UserMission> userMissionPage =
                userMissionRepository.findUserMissionsByStatus(memberId, status, pageable);

        Page<MissionResponseDto.MissionPreviewDTO> result =
                userMissionPage.map(MissionConverter::toMissionPreviewDTO);

        return MissionConverter.toMissionPreviewListDTO(result);
    }
}