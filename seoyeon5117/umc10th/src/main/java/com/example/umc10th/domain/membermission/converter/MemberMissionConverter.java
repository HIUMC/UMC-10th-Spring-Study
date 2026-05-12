package com.example.umc10th.domain.membermission.converter;

import com.example.umc10th.domain.membermission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.membermission.entity.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MemberMissionConverter {

    public static MemberMissionResDTO.GetHomeMemberMission toGetHomeMemberMission(MemberMission mm) {
        return MemberMissionResDTO.GetHomeMemberMission.builder()
                .storeId(mm.getMission().getStore().getId())
                .storeName(mm.getMission().getStore().getName())
                .foodType(mm.getMission().getStore().getFood())
                .dueDate(mm.getDueDate())
                .missionPoint(mm.getMission().getPoint())
                .minPrice(mm.getMission().getMinPrice())
                .build();
    }

    public static MemberMissionResDTO.GetHomeMemberMissions toGetHomeMemberMissions(Page<MemberMission> page, Integer achievedCount, Integer totalCount) {
        Page<MemberMissionResDTO.GetHomeMemberMission> missions = page.map(MemberMissionConverter::toGetHomeMemberMission);
        return MemberMissionResDTO.GetHomeMemberMissions.builder()
                .achievedCount(achievedCount)
                .totalCount(totalCount)
                .missions(missions)
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    public static MemberMissionResDTO.GetMemberMission toGetMemberMission(MemberMission mm) {
        return MemberMissionResDTO.GetMemberMission.builder()
                .storeId(mm.getMission().getStore().getId())
                .storeName(mm.getMission().getStore().getName())
                .missionPoint(mm.getMission().getPoint())
                .status(mm.getStatus())
                .build();
    }

    public static <T> MemberMissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {
        return MemberMissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
