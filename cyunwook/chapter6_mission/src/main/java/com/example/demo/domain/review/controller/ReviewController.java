package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.ReviewReqDTO;
import com.example.demo.domain.review.dto.ReviewResDTO;
import com.example.demo.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    @Operation(summary = "리뷰 작성")
    public ResponseEntity<ReviewResDTO> createReview(
            @RequestBody @Valid ReviewReqDTO request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }
}
