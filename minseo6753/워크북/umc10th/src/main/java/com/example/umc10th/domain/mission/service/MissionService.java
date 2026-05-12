package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.restaurant.entity.address.EupMyeonDong;
import com.example.umc10th.domain.restaurant.exception.EupMyeonDongException;
import com.example.umc10th.domain.restaurant.exception.code.EupMyeonDongErrorCode;
import com.example.umc10th.domain.restaurant.repository.EupMyeonDongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final EupMyeonDongRepository eupMyeonDongRepository;

    public MissionResDTO.InfoSlice getMissions(Long eupMyeonDongId, Long cursor) {

        EupMyeonDong eupMyeonDong = eupMyeonDongRepository.findById(eupMyeonDongId)
                .orElseThrow(() -> new EupMyeonDongException(EupMyeonDongErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(0, 10);
        Slice<Mission> missionSlice;

        if (cursor == null) {
            missionSlice = missionRepository.findFirstPage(eupMyeonDong, pageRequest);
        } else {

            Mission lastFound = missionRepository.findById(cursor)
                    .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

            missionSlice = missionRepository.findNextPage(eupMyeonDong, lastFound.getDeadline(), lastFound.getId(), pageRequest);
        }

        return MissionConverter.toInfoSlice(missionSlice);
    }

}
