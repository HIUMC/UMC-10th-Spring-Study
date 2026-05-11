package umc10th.assignment.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewRequestDto {
    public record CreateReview(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,

            @NotNull(message = "별점은 필수입니다.")
            @DecimalMin(value = "0.0", message = "별점은 0점 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5점 이하여야 합니다.")
            Float star,

            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String content
    ) {
    }
    public record GetMyReview(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId
    ) {
    }
}
