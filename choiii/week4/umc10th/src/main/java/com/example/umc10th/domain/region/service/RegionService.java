package com.example.umc10th.domain.region.service;

import com.example.umc10th.domain.region.dto.request.RegionCreateRequest;
import com.example.umc10th.domain.region.dto.response.RegionResponse;
import com.example.umc10th.domain.region.entity.Region;
import com.example.umc10th.domain.region.repository.RegionRepository;
import com.example.umc10th.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionService {

    private final RegionRepository regionRepository;

    @Transactional
    public RegionResponse createRegion(RegionCreateRequest request) {
        Region region = regionRepository.save(Region.builder().regionName(request.regionName()).build());
        return new RegionResponse(region.getId(), region.getRegionName());
    }

    public Region findRegion(Long regionId) {
        return regionRepository.findById(regionId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 지역입니다. regionId=" + regionId));
    }
}
