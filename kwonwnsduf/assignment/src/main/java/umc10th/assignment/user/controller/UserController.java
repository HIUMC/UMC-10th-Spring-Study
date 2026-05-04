package umc10th.assignment.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import umc10th.assignment.global.apiPayload.ApiResponse;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;
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
            @RequestParam Long memberId
    ) {
        BaseSuccessCode code = UserSuccessCode.GET_MY_PAGE;

        UserResponseDto.MyPageDTO myPage =
                userService.getMyPage(memberId);

        UserResponseDto.GetInfo response =
                UserResponseDto.GetInfo.builder()
                        .name(myPage.nickname())
                        .profileUrl("https://example.com/profile.png")
                        .email(myPage.email())
                        .phoneNumber(myPage.phoneNumber())
                        .point(myPage.point())
                        .build();

        return ApiResponse.onSuccess(code, response);
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
            @RequestParam Long memberId
    ) {
        BaseSuccessCode code = UserSuccessCode.GET_POINT;

        UserResponseDto.MyPageDTO myPage =
                userService.getMyPage(memberId);

        UserResponseDto.GetPoint response =
                UserResponseDto.GetPoint.builder()
                        .point(myPage.point())
                        .build();

        return ApiResponse.onSuccess(code, response);
    }
}