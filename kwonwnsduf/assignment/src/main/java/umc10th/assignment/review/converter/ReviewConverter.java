package umc10th.assignment.review.converter;
import umc10th.assignment.review.dto.ReviewRequestDto;
import umc10th.assignment.review.dto.ReviewResponseDto;
import umc10th.assignment.review.entity.Review;
import umc10th.assignment.store.entity.Store;
import umc10th.assignment.user.entity.User;

import java.util.List;

public class ReviewConverter {
    public static Review toReview(
            ReviewRequestDto.CreateReview request,
            User user,
            Store store
    ) {
        return Review.builder()
                .content(request.content())
                .star(request.star())
                .user(user)
                .store(store)
                .build();
    }

    public static ReviewResponseDto.CreateReview toCreateReviewResultDTO(Review review) {
        return ReviewResponseDto.CreateReview.builder()
                .reviewId(review.getReviewId())
                .storeId(review.getStore().getStoreId())
                .memberId(review.getUser().getMemberId())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDto.GetMyReview toGetMyReview(Review review) {
        return ReviewResponseDto.GetMyReview.builder()
                .reviewId(review.getReviewId())
                .storeName(review.getStore().getName())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static <T> ReviewResponseDto.CursorPagination<T> toCursorPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return ReviewResponseDto.CursorPagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
    }

