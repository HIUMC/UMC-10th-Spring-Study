package umc10th.assignment.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import umc10th.assignment.global.apiPayload.ApiResponse;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;
import umc10th.assignment.global.security.entity.AuthMember;
import umc10th.assignment.user.code.UserSuccessCode;
import umc10th.assignment.user.dto.UserRequestDto;
import umc10th.assignment.user.dto.UserResponseDto;
import umc10th.assignment.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 마이페이지 조회
    @GetMapping("/members/me")
    public ApiResponse<UserResponseDto.GetInfo> getInfo(
            @AuthenticationPrincipal AuthMember member
    ) {
        BaseSuccessCode code = UserSuccessCode.GET_MY_PAGE;
        return ApiResponse.onSuccess(code, userService.getInfo(member));
    }

    // 내 정보 수정 - 아직 서비스 없이 기존 방식 유지
    @PatchMapping("/members/me")
    public ApiResponse<UserResponseDto.UpdateInfo> updateInfo(
            @RequestBody UserRequestDto.UpdateInfo dto
    ) {
        BaseSuccessCode code = UserSuccessCode.UPDATE_USER_INFO;

        UserResponseDto.UpdateInfo response =
                UserResponseDto.UpdateInfo.builder()
                        .name(dto.name())
                        .profileUrl(dto.profileUrl())
                        .phoneNumber(dto.phoneNumber())
                        .build();

        return ApiResponse.onSuccess(code, response);
    }

    // 내 포인트 조회
    @GetMapping("/members/me/points")
    public ApiResponse<UserResponseDto.GetPoint> getPoint(
            @AuthenticationPrincipal AuthMember member
    ) {
        BaseSuccessCode code = UserSuccessCode.GET_POINT;
        return ApiResponse.onSuccess(code, userService.getPoint(member));
    }
  }

