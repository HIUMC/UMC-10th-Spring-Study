package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(value = "/restaurants/{restaurantId}/reviews", consumes = "multipart/form-data")
    public ApiResponse<ReviewResDTO.Info> createReview(
            @RequestHeader Long memberId, //todo 토큰인증으로 변경
            @PathVariable Long restaurantId,
            @RequestPart(value = "data")
            @Parameter(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReviewReqDTO.Create.class)
                    )
            )
            ReviewReqDTO.Create dto,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                reviewService.createReview(memberId, restaurantId, dto, files)
        );
    }

    @GetMapping(value = "/users/reviews")
    public ApiResponse<ReviewResDTO.Slice<ReviewResDTO.Info>> getReview(
            @RequestHeader Long memberId, //todo 토큰인증으로 변경
            @RequestParam Integer pageSize,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "id") String query
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewService.getReview(memberId, pageSize, cursor, query)
        );
    }
}
