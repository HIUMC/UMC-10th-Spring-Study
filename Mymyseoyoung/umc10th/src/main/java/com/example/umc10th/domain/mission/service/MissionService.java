package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Long.parseLong;

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
            String cursor,
            String query) {
//페이지 정보들을 page Request로 만들기
        PageRequest pageRequest=PageRequest.of(0,pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor;

        //커서가 있는 경우
        if(!cursor.equals("-1"))
        {

            //커서 분리
            String [] cursorSplit = cursor.split(":");
            switch(query.toLowerCase())
            {
                case "id":

                    //커서 타입 변환
                    Long prevCursor = parseLong(cursorSplit[0]);
                    idCursor = parseLong(cursorSplit[1]);

                    //가게 내 미션들 조회 & where 절에 커서 값 기입
                    missionList = missionRepository.findMissionsByStore_IdAndIdLessThanOrderByIdDesc(
                            storeId,
                            idCursor
                            ,pageRequest
                    );
                    break;
                    default:
                        throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);

            }

        }
        else {
            // 커서 없이 조회
            missionList = missionRepository.findMissionsByStore_IdOrderByIdDesc(storeId, pageRequest);
        }

        // 다음 커서 계산
        if (missionList.getContent().isEmpty()) {
            nextCursor = "-1";
        } else {
            Long lastId = missionList.getContent().getLast().getId();
            nextCursor = lastId + ":" + lastId;
        }

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );

    }

}