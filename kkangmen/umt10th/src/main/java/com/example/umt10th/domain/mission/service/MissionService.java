package com.example.umt10th.domain.mission.service;

import com.example.umt10th.domain.mission.converter.MissionConverter;
import com.example.umt10th.domain.mission.dto.MissionReqDTO;
import com.example.umt10th.domain.mission.dto.MissionResDTO;
import com.example.umt10th.domain.mission.entity.Mission;
import com.example.umt10th.domain.mission.entity.Store;
import com.example.umt10th.domain.mission.exception.MissionException;
import com.example.umt10th.domain.mission.exception.StoreException;
import com.example.umt10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umt10th.domain.mission.exception.code.StoreErrorCode;
import com.example.umt10th.domain.mission.repository.MemberMissionRepository;
import com.example.umt10th.domain.mission.repository.MissionRepository;
import com.example.umt10th.domain.mission.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    /***
     * 미션 성공 누르기
     * @param missionId
     * @return
     */
    public MissionResDTO.MissionSuccessDto succeedMission(Long missionId) {

        // DB에서 해당 미션 Status를 완료로 바꿔준다.
        return new MissionResDTO.MissionSuccessDto(LocalDateTime.now());
    }

    /***
     * 가게 미션 생성
     */
    @Transactional
    public Void createMission(Long storeId, MissionReqDTO.CreateMission dto){

        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        // 미션 DB 저장
        missionRepository.save(mission);
        return null;
    }

    /***
     * 가게 내 미션들 조회
     */
    @Transactional
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ){

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor = "";

        // 커서가 있는 경우
        if (!cursor.equals("-1")){

            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()){
                case "id":

                    // 커서 타입 변환
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    // 가게 내 미션들 조회 & where 절에 커서값 기입
                    missionList = missionRepository.findMissionByStore_IdAndIdLessThanOrderByIdDesc(
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
            missionList = missionRepository.findMissionByStore_IdOrderByIdDesc(storeId, pageRequest);
        }

        // 타음 커서 계산
        nextCursor = missionList.getContent().getLast().getId() + ":" +
                missionList.getContent().getLast().getId();

        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }

    /**
     * 내가 진행 중인 미션 / 진행 완료한 미션 조회
     * @param dto
     * @param status
     * @param pageSize
     * @param cursor
     * @return
     */
    @Transactional
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMyMissions(
            MissionReqDTO.GetMission dto,
            boolean status,
            Integer pageSize,
            String cursor
    ){
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Mission> missions;
        String nextCursor;

        if (!cursor.equals("-1")){
            idCursor = Long.parseLong(cursor);
            missions = missionRepository.findOngoingMissions(dto.memberId(), status, idCursor, pageRequest);

        } else {
            missions = missionRepository.findOngoingMissionsWithoutCursor(dto.memberId(), status, pageRequest);
        }

        if (missions.isEmpty()){
            nextCursor = "-1";
        } else {
            nextCursor = String.valueOf(missions.getContent().getLast().getId());
        }

        return MissionConverter.toPagination(
                missions.map(MissionConverter::toGetMission).toList(),
                missions.hasNext(),
                nextCursor,
                pageSize);
    }
}
