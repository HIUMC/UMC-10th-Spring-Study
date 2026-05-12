package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findAllByStore_Id(Long missionId, PageRequest pageRequest);

    Slice<Mission> findMissionsByStore_IdAndIdLessThanOrderByIdDesc(Long storeId, long idCursor, PageRequest pageRequest);

    Slice<Mission> findMissionsByStore_IdOrderByIdDesc(Long storeId, PageRequest pageRequest);
}
