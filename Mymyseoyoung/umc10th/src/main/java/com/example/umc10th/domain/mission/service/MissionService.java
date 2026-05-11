package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public MissionResponseDTO.MissionListDTO getAvailableMissions(Long regionId, Long memberId, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Mission> result = missionRepository.findAvailableMissionsByRegion(regionId, memberId, pageable);

        List<MissionResponseDTO.MissionDetailDTO> missionList = result.getContent()
                .stream()
                .map(m -> MissionResponseDTO.MissionDetailDTO.builder()
                        .missionId(m.getId())
                        .title(m.getTitle())
                        .description(m.getDescription())
                        .reward(m.getRewardPoints())
                        .address(m.getStore().getRegion().getName())
                        .regionId(m.getStore().getRegion().getId())
                        .regionName(m.getStore().getRegion().getName().getDescription())
                        .build())
                .toList();

        return MissionResponseDTO.MissionListDTO.builder()
                .missionList(missionList)
                .hasNext(result.hasNext())
                .page(page)
                .build();
    }

    //가게 미션 생성

    public Void createMission(Long storeId, MissionRequestDTO.CreateMission request)
    {
        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        //미션 생성
        Mission mission = MissionConverter.toMission(store, request);

        //미션 DB 저장
        missionRepository.save(mission);

        return null;

    }

    //가게 내 미션들 조회

    public MissionResponseDTO.Pagination<MissionResponseDTO.GetMission> getStoreMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort) {


        //정렬 정보 생성

        Sort sortInfo;

        if(sort!=null)
        {
            sortInfo=Sort.by(sort);
        }else
        {
            sortInfo=Sort.by("id").descending();

        }

        //페이지 정보들을 page Request로 만들기
        PageRequest pageRequest=PageRequest.of(pageNumber,pageSize,sortInfo);

        //가게 내 미션 조회

        Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId,pageRequest);

        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );

    }

}