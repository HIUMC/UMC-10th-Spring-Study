package com.example.umc10th.domain.membermission.service;

import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.usermission.entity.UserMission;
import com.example.umc10th.domain.usermission.enums.UserMissionStatus;
import com.example.umc10th.domain.usermission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMissionService {


    private final UserMissionRepository userMissionRepository;

    //진행중 / 진행 완료한 미션 조회 ( missionstatus 별로 )
    public MemberResponseDTO.GetMyMissionListInfo getMyMissions(
            Long memberId, UserMissionStatus status, int page) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<UserMission> result = userMissionRepository
                .findByMemberIdAndStatus(memberId, status, pageable);

        List<MemberResponseDTO.MissionInfo> missionList = result.getContent()
                .stream()
                .map(um -> MemberResponseDTO.MissionInfo.builder()
                        .userMissionId(um.getId())
                        .storeName(um.getMission().getStore().getName())
                        .missionDescription(um.getMission().getDescription())
                        .rewardPoints(um.getMission().getRewardPoints())
                        .status(um.getStatus().name())
                        .build())
                .toList();

        return MemberResponseDTO.GetMyMissionListInfo.builder()
                .missionList(missionList)
                .hasNext(result.hasNext())
                .page(page)
                .build();
    }

}
