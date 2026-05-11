package umc10th.assignment.usermission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc10th.assignment.usermission.converter.UserMissionConverter;
import umc10th.assignment.usermission.dto.UserMissionRequestDto;
import umc10th.assignment.usermission.dto.UserMissionResponseDto;
import umc10th.assignment.usermission.entity.MissionStatus;
import umc10th.assignment.usermission.entity.UserMission;
import umc10th.assignment.usermission.repository.UserMissionRepository;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;

    public UserMissionResponseDto.Pagination<UserMissionResponseDto.GetMyMission> getMyOngoingMissions(
            UserMissionRequestDto.GetMyMission dto,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        Sort sortInfo;

        if (sort != null && !sort.isBlank()) {
            sortInfo = Sort.by(sort).descending();
        } else {
            sortInfo = Sort.by("memberMissionId").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<UserMission> userMissionPage =
                userMissionRepository.findUserMissionsByStatus(
                        dto.memberId(),
                        MissionStatus.CHALLENGING,
                        pageRequest
                );

        Page<UserMissionResponseDto.GetMyMission> result =
                userMissionPage.map(UserMissionConverter::toGetMyMission);

        return UserMissionConverter.toPagination(result);
    }

}
