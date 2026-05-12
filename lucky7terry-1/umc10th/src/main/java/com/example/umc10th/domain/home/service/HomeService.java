package com.example.umc10th.domain.home.service;

import com.example.umc10th.domain.home.converter.HomeConverter;
import com.example.umc10th.domain.home.dto.HomeReqDTO;
import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Region;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final MissionRepository missionRepository;
    private final RegionRepository regionRepository;

    @Transactional(readOnly = true)
    public HomeResDTO.HomeResponseDTO getHome(HomeReqDTO.HomeRequest dto) {
        Long memberId = dto.memberId();
        Long locateId = dto.locateId();
        int pageSize = normalizeSize(dto.size());
        Pageable pageable = PageRequest.of(0, pageSize + 1);

        Region region = regionRepository.findById(locateId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지역입니다."));

        List<Mission> rows = missionRepository.findAvailableMissionsByRegion(
                locateId,
                memberId,
                dto.cursor() == null ? 0L : dto.cursor(),
                LocalDate.now(),
                pageable
        );

        boolean hasNext = rows.size() > pageSize;
        List<Mission> content = hasNext ? rows.subList(0, pageSize) : rows;
        Long nextCursor = content.isEmpty() ? null : content.get(content.size() - 1).getId();
        Long clearedMissionCount = missionRepository.countCompletedMissionsByMemberId(memberId);

        return HomeConverter.toHomeResponseDTO(region, clearedMissionCount, content, hasNext, nextCursor);
    }

    private int normalizeSize(Integer size) {
        if (size == null || size < 1) {
            return 10;
        }

        return Math.min(size, 20);
    }
}
