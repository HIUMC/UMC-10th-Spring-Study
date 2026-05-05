package umc10th.assignment.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc10th.assignment.global.apiPayload.ApiResponse;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;
import umc10th.assignment.store.dto.StoreResponseDto;
import umc10th.assignment.store.exception.code.StoreSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoreController {
    @GetMapping("/home")
    public ApiResponse<StoreResponseDto.Home> getHome() {
        BaseSuccessCode code = StoreSuccessCode.GET_HOME;

        StoreResponseDto.HomeMission mission = StoreResponseDto.HomeMission.builder()
                .missionId(1L)
                .title("리뷰 작성 미션")
                .storeName("맛있는 식당")
                .reward(500)
                .build();

        StoreResponseDto.Home response = StoreResponseDto.Home.builder()
                .userName("nickname012")
                .missions(List.of(mission))
                .build();

        return ApiResponse.onSuccess(code, response);
    }

}
