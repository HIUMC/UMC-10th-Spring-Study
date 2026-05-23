package com.example.umc10th.domain.mission.service;


import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {


    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.MissionListDTO getMyMissions(Long memberId, Status status,String cursor){

        List<Mission> mission;


        mission = missionRepository.findAll();


        return MissionConverter.toMissionList(mission);
    }


    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMission(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort) {

                Sort sortInfo;

                if(sort != null){
                    sortInfo = Sort.by(sort);
                } else {
                    sortInfo = Sort.by("id").descending();
                }

                PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sortInfo);

                Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId, pageRequest);

                return MissionConverter.toPagination(
                        missionList.map(MissionConverter::toGetMission).toList(),
                        missionList.getNumber(),
                        missionList.getSize()
                );
    }

    //가게 미션 생성
    @Transactional
    public Void createMission(Long storeId, MissionReqDTO.CreateMission dto) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(()->new StoreException(StoreErrorCode.NOT_FOUND));

        //미션 생성
        Mission mission = MissionConverter.tMission(store,dto);

        missionRepository.save(mission);

        return null;
    }

    public MissionResDTO.Pagination<MissionResDTO.GetMission> getProgressMission(
            Long loginId,
            Integer pageSize,
            Integer pageNumber) {


        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);

        Page<MemberMission> missionList = memberMissionRepository.findByMemberIdAndStatus(loginId,false, pageRequest);

        return MissionConverter.toPagination(
                missionList.map(MissionConverter::getProgressMissions).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }


}
