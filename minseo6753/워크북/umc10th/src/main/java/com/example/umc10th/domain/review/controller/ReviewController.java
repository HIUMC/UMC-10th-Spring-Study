package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants/{restaurantId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(value = "/", consumes = "multipart/form-data")
    public ApiResponse<ReviewResDTO.Info> createReview(
            @PathVariable Long restaurantId,
            @RequestParam Integer star,
            @RequestParam String content,
            @RequestParam List<MultipartFile> files
            ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewService.createReview(restaurantId, star, content, files)
        );
    }
}
