package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewReqDTO {

    /* ───────────── 리뷰 작성 ───────────── */
    @Getter
    @NoArgsConstructor
    public static class ReviewWriteReqDTO {

        @NotBlank(message = "사용자 ID는 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String userId;

        @NotNull(message = "별점은 필수입니다.")
        @Min(value = 1, message = "별점은 최소 1점입니다.")
        @Max(value = 5, message = "별점은 최대 5점입니다.")
        private Integer rating;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        private String reviewContent;

        private String photoUrl; // (선택) 사진 URL
    }
}
