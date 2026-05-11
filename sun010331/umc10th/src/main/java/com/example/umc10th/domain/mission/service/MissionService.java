package com.example.umc10th.domain.mission.service;


import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {


    private final MissionRepository missionRepository;

    public MissionResDTO.MissionListDTO getMyMissions(Long memberId, Status status,String cursor){

        List<Mission> mission;


        mission = missionRepository.findAll();


        return MissionConverter.toMissionList(mission);
    }


}
