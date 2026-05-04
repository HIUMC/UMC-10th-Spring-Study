package umc10th.assignment.review.service;

import lombok.RequiredArgsConstructor;
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
}