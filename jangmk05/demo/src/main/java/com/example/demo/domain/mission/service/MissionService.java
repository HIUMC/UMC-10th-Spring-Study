package com.example.demo.domain.mission.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.MemberException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.mission.converter.MissionConverter;
import com.example.demo.domain.mission.dto.MissionReqDTO;
import com.example.demo.domain.mission.dto.MissionResDTO;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.Store;
import com.example.demo.domain.mission.entity.mapping.MemberMission;
import com.example.demo.domain.mission.enums.MissionStatus;
import com.example.demo.domain.mission.exception.MissionException;
import com.example.demo.domain.mission.exception.StoreExceptinon;
import com.example.demo.domain.mission.exception.code.MissionErrorCode;
import com.example.demo.domain.mission.exception.code.StoreErrorCode;
import com.example.demo.domain.mission.repository.MemberMissionRepository;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.domain.mission.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public MissionResDTO.MyMissionPreviewListDTO getMyMissions(Long memberId, MissionStatus status, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // Spring Data JPA의 페이징 기능
        // memberId, status 조건에 맞는 MemberMission 엔티티를 한 번에 다 가져오지 말고, page 단위로 끊어서 가져옴
        Page<MemberMission> memberMissionPage = memberMissionRepository
                .findAllByMemberAndMissionStatus(member, status, PageRequest.of(page, 10));

        return MissionConverter.toMyMissionPreviewListDTO(memberMissionPage);
    }

    public MissionResDTO.AvailableMissionListDTO getAvailableMissions(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Page<Mission> missionPage = missionRepository
                .findAvailableMissionsByRegion(member, member.getAddress(),PageRequest.of(page, 10));

        return MissionConverter.toAvailableMissionListDTO(missionPage);
    }

    // 가게 미션 생성
    @Transactional
    public Void createMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ) {
        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreExceptinon(StoreErrorCode.NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        // 미션 DB 저장
        missionRepository.save(mission);
        return null;
    }

    // 가게 내 미션들 조회
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
        if (!cursor.equals("-1")) {
            // 커서 분리
            String[] split = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":

                    // 커서 타입 변환
                    long prevCursor = Long.parseLong(split[0]);
                    idCursor = Long.parseLong(split[1]);

                    // 💡 수정 1: 가져온 데이터를 missionList에 담아주기
                    missionList = missionRepository.findMissionsByStore_IdAndIdLessThanOrderByIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new MissionException(MissionErrorCode.MISSION_STATUS_INVALID);
            }

        } else { // 💡 수정 2: 괄호를 닫고 else를 붙여서 커서가 "-1"일 때를 처리해주기
            // 커서 없이 조회
            missionList = missionRepository.findMissionByStore_IdOrderByIdDesc(storeId, pageRequest);
        }

        // 다음 커서 계산
        nextCursor = String.valueOf(missionList.getContent().get(missionList.getContent().size() - 1).getId());

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }
}