package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.StoreException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.exception.code.StoreErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    //
    @Transactional(readOnly = true)
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMyMissions(
            MissionReqDTO.GetMission dto,
            boolean status,
            Integer pageSize,
            String cursor
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Mission> missionSlice;
        String nextCursor;

        if(!cursor.equals("-1")){
            String[] split = cursor.split(":");
            idCursor = Long.parseLong(split[0]);
            missionSlice = missionRepository.findOngoingMissions(dto.memberId(), status, idCursor, pageRequest);
        } else {
            missionSlice = missionRepository.findOngoingMissionsWithoutCursor(dto.memberId(), status, pageRequest);
        }

        if(missionSlice.isEmpty()) {
            nextCursor = "-1";
        } else {
            nextCursor = String.valueOf(missionSlice.getContent().getLast().getId());
        }

        return MissionConverter.toPagination(
                missionSlice.map(MissionConverter::toGetMission).toList(),
                missionSlice.hasNext(),
                nextCursor,
                pageSize);
    }

    // 미션 성공 누르기
    @Transactional
    public MissionResDTO.MissionCompleteDTO completeMission(Long missionId) {

        return new MissionResDTO.MissionCompleteDTO(LocalDateTime.now());
    }

    // 가게 미션 생성
    public Void createMission(Long storeId, MissionReqDTO.@Valid CreateMission dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, dto);

        missionRepository.save(mission);

        return null;
    }

    // 가게 내 미션 조회
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getStoreMissions(Long storeId, Integer pageSize, String cursor, String query) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Mission> missionSlice;
        String nextCursor = "";

        // 커서가 있는 경우
        if(!cursor.equals("-1")) {
            String[] split = cursor.split(":");
            switch (query.toLowerCase()){
                case "id":
                    // 커서 타입 변환
                    long prevCursor = Long.parseLong(split[0]);
                    idCursor = Long.parseLong(split[1]);

                    missionSlice = missionRepository.findMissionByStore_IdAndIdLessThanOrderByIdDesc(
                            storeId,
                            idCursor,
                            prevCursor
                    );

                    break;
                default:
                    throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }
        } else {
            missionSlice = missionRepository.findMissionByStore_IdOrderByIdDesc(storeId,pageRequest);
        }

        // 다음 커서 계산
        nextCursor = missionSlice.getContent().getLast().getId() + ":" + missionSlice.getContent().getLast().getId();

        return MissionConverter.toPagination(
                missionSlice.map(MissionConverter::toGetMission).toList(),
                missionSlice.hasNext(),
                nextCursor,
                missionSlice.getSize());

    }
}
