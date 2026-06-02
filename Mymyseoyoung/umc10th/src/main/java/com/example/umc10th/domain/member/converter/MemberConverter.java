package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.dto.TokenResponse;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.FoodPreference;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.usermission.entity.UserMission;

import java.util.List;

public class MemberConverter {

    // 마이페이지 응답 DTO 변환
    public static MemberResponseDTO.GetInfo toGetInfo(Member user) {
        return MemberResponseDTO.GetInfo.builder()
                .name(user.getName())
                .email(user.getEmail())
                .point(user.getPoint())
                .phoneNumber(user.getPhoneNumber())
                .profileUrl(user.getProfileUrl())
                .build();
    }

    public static Member toMember(MemberRequestDTO.Join request,String encodedPassword) {
        return Member.builder()
                .email(request.email())// 주의: 실제 구현 시에는 PasswordEncoder로 암호화 필요
                .name(request.name())
                .password(encodedPassword)
                .gender(request.gender())
                .birth(request.birth())
                .address(request.address())
                .phoneNumber(request.phoneNumber())
                .point(0) // 초기 포인트는 0으로 설정
                .build();
    }

    // FoodPreference 리스트 변환
    public static List<FoodPreference> toFoodPreferenceList(Member member, List<Food> foods) {
        return foods.stream()
                .map(food -> FoodPreference.builder()
                        .member(member)
                        .food(food)
                        .build())
                .toList();
    }

    public static MemberResponseDTO.JoinResult toJoinResult(Member member, String token) {
        return MemberResponseDTO.JoinResult.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .accessToken(token)
                .build();
    }
    public static MemberResponseDTO.GetMyPointInfo toGetMyPointInfo(Member member) {
        return MemberResponseDTO.GetMyPointInfo.builder()
                .memberId(member.getId())
                .name(member.getName())
                .point(member.getPoint())
                .build();
    }

    public static MemberResponseDTO.MissionInfo toMissionInfo(UserMission userMission) {
        return MemberResponseDTO.MissionInfo.builder()
                .userMissionId(userMission.getId())
                .storeName(userMission.getMission().getStore().getName())
                .missionDescription(userMission.getMission().getDescription())
                .rewardPoints(userMission.getMission().getRewardPoints())
                .status(userMission.getStatus().name())
                .build();
    }

    public static MemberResponseDTO.Pagination<MemberResponseDTO.MissionInfo> toGetMyMissionListInfo(
            List<MemberResponseDTO.MissionInfo> missionList,
            int pageNumber,
            int pageSize) {

        return MemberResponseDTO.Pagination.<MemberResponseDTO.MissionInfo>builder()
                .data(missionList)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
