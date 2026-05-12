package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.MissionErrorCode;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.enums.StoreErrorCode;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public MissionResDTO.CreateMission createMission(Long storeId, MissionReqDTO.CreateMission dto) {
        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        // 미션 DB 저장
        missionRepository.save(mission);
        return null;
    }

    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
        ) {
        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor;

        // 커서가 있는 경우
        if(!cursor.equals("-1")) {

            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            switch(query.toLowerCase()) {
                case "id":

                    // 커서 타입 변환
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    // 가게 내 미션들 조회 & where절에 커서값 기입
                    missionList = missionRepository.findMissionsByStore_IdAndIdLessThanOrderByIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }
        } else {

            // 커서 없이 조회
            missionList = missionRepository.findMissionsByStore_IdOrderByIdDesc(storeId, pageRequest);
        }

        // 다음 커서 계산
        nextCursor = missionList.getContent().getLast().getId() + ":" + missionList.getContent().getLast().getId();

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }

    public MissionResDTO.GetMission getMission(Long missionId) {
        return null;
    }

    public MissionResDTO.UpdateMission updateMission(Long missionId, MissionReqDTO.UpdateMission dto) {
        return null;
    }

    public void deleteMission(Long missionId) {
    }
}
