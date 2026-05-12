package umc10th.assignment.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc10th.assignment.mission.entity.Mission;
import umc10th.assignment.mission.repository.MissionRepository;
import umc10th.assignment.review.converter.ReviewConverter;
import umc10th.assignment.review.dto.ReviewRequestDto;
import umc10th.assignment.review.dto.ReviewResponseDto;
import umc10th.assignment.review.entity.Review;
import umc10th.assignment.review.repository.ReviewRepository;
import umc10th.assignment.store.entity.Store;
import umc10th.assignment.store.repository.StoreRepository;
import umc10th.assignment.user.entity.User;
import umc10th.assignment.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public ReviewResponseDto.CreateReview createReview(
            Long missionId,
            ReviewRequestDto.CreateReview request
    ) {
        User user = userRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        Review review = ReviewConverter.toReview(
                request,
                user,
                mission.getStore()
        );

        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toCreateReviewResultDTO(savedReview);
    }
    public ReviewResponseDto.CursorPagination<ReviewResponseDto.GetMyReview> getMyReviews(
            ReviewRequestDto.GetMyReview dto,
            Integer pageSize,
            String cursor,
            String query
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize + 1);

        List<Review> reviewList;

        if (cursor == null || cursor.isBlank() || cursor.equals("-1")) {
            reviewList = getReviewsWithoutCursor(dto.memberId(), query, pageRequest);
        } else {
            reviewList = getReviewsWithCursor(dto.memberId(), cursor, query, pageRequest);
        }

        boolean hasNext = reviewList.size() > pageSize;

        List<Review> content = hasNext
                ? reviewList.subList(0, pageSize)
                : reviewList;

        String nextCursor = makeNextCursor(content, query, hasNext);

        List<ReviewResponseDto.GetMyReview> data = content.stream()
                .map(ReviewConverter::toGetMyReview)
                .toList();

        return ReviewConverter.toCursorPagination(
                data,
                hasNext,
                nextCursor,
                pageSize
        );
    }

    private List<Review> getReviewsWithoutCursor(
            Long memberId,
            String query,
            PageRequest pageRequest
    ) {
        if (query == null || query.isBlank() || query.equalsIgnoreCase("id")) {
            return reviewRepository.findMyReviewsOrderByIdDesc(memberId, pageRequest);
        }

        if (query.equalsIgnoreCase("star")) {
            return reviewRepository.findMyReviewsOrderByStarDesc(memberId, pageRequest);
        }

        throw new IllegalArgumentException("지원하지 않는 정렬 기준입니다.");
    }

    private List<Review> getReviewsWithCursor(
            Long memberId,
            String cursor,
            String query,
            PageRequest pageRequest
    ) {
        if (query == null || query.isBlank() || query.equalsIgnoreCase("id")) {
            String[] cursorSplit = cursor.split(":");
            Long cursorId = Long.parseLong(cursorSplit[cursorSplit.length - 1]);

            return reviewRepository.findMyReviewsByIdCursor(
                    memberId,
                    cursorId,
                    pageRequest
            );
        }

        if (query.equalsIgnoreCase("star")) {
            String[] cursorSplit = cursor.split(":");

            Float cursorStar = Float.parseFloat(cursorSplit[0]);
            Long cursorId = Long.parseLong(cursorSplit[1]);

            return reviewRepository.findMyReviewsByStarCursor(
                    memberId,
                    cursorStar,
                    cursorId,
                    pageRequest
            );
        }

        throw new IllegalArgumentException("지원하지 않는 정렬 기준입니다.");
    }

    private String makeNextCursor(
            List<Review> content,
            String query,
            boolean hasNext
    ) {
        if (!hasNext || content.isEmpty()) {
            return null;
        }

        Review lastReview = content.get(content.size() - 1);

        if (query != null && query.equalsIgnoreCase("star")) {
            return lastReview.getStar() + ":" + lastReview.getReviewId();
        }

        return lastReview.getReviewId() + ":" + lastReview.getReviewId();
    }
}