package com.example.demo.domain.mission.service;

import com.example.demo.domain.mission.converter.MissionConverter;
import com.example.demo.domain.mission.dto.MissionReqDTO;
import com.example.demo.domain.mission.dto.MissionResDTO;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.Store;
import com.example.demo.domain.mission.entity.mapping.MemberMission;
import com.example.demo.domain.mission.exception.StoreException;
import com.example.demo.domain.mission.exception.code.StoreErrorCode;
import com.example.demo.domain.mission.repository.MemberMissionRepository;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.domain.mission.repository.StoreRepository;
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
@Transactional(readOnly = true)
public class MissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 가게 미션 생성
    @Transactional
    public Void createMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ) {
        //가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        //미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        //미션 DB저장
        missionRepository.save(mission);
        return null;
    }



    // 홈 화면 - 지역별 미션 조회
    public MissionResDTO.MissionPageDTO getMissionsByLocation(
            Long locationId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Mission> result = missionRepository.findByLocationId(locationId, pageable);

        List<MissionResDTO> dtos = result.getContent().stream()
                .map(m -> MissionResDTO.builder()
                        .missionId(m.getId())
                        .storeName(m.getStore().getName())
                        .conditional(m.getConditional())
                        .point(m.getPoint())
                        .deadline(m.getDeadline())
                        .build())
                .toList();

        return MissionResDTO.MissionPageDTO.builder()
                .missions(dtos)
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .currentPage(page)
                .build();
    }
    //가게 내 미션들 조회
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {

        //정렬 정보 생성
        Sort sortInfo;
        if (sort != null){
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }
        //페이지 정보들을 pagerequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);
        //가게 내 미션들 조회
        Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId, pageRequest);

        //미션들 응답 dto로 포장
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }

    //진행중인 미션 조회
    public MissionResDTO.MissionPageDTO getMyInProgressMissions(
            Long memberId, int page, int size) {
                Pageable pageable = PageRequest.of(page, size);
                Page<MemberMission> result =
                        memberMissionRepository.findByMemberIdAndStatus (memberId, false, pageable);

                return MissionConverter.toMissionPageDTO(result);
    }

}
