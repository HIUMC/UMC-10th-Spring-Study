package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.restaurant.exception.RestaurantException;
import com.example.umc10th.domain.restaurant.exception.code.RestaurantErrorCode;
import com.example.umc10th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO.Create;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    public ReviewResDTO.Info createReview(Long memberId, Long restaurantId, Create dto, List<MultipartFile> files) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(RestaurantErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toReview(member, restaurant, dto);
        reviewRepository.save(review);
        return ReviewConverter.toInfo(review);
    }

    public ReviewResDTO.Slice<ReviewResDTO.Info> getReview(Long memberId, Integer pageSize, Long cursor, String query) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Slice<Review> reviewSlice;

        switch (query.toLowerCase()) {
            case "id":
                if (cursor == null) {
                    reviewSlice = reviewRepository.findByMemberOrderByIdDesc(member, pageRequest);
                } else {
                    reviewSlice = reviewRepository.findByMemberAndIdLessThanOrderByIdDesc(member, cursor, pageRequest);
                }
                break;
            case "star":
                if (cursor == null) {
                    reviewSlice = reviewRepository.findByMemberOrderByStarDescIdDesc(member, pageRequest);
                } else {
                    Review lastFound = reviewRepository.findById(cursor)
                            .orElseThrow(() -> new ReviewException(ReviewErrorCode.INVALID_CURSOR));

                    reviewSlice = reviewRepository.findNextSliceByMemberOrderByStar(member, lastFound.getStar(),
                            lastFound.getId(), pageRequest);
                }
                break;
            default:
                throw new ReviewException(ReviewErrorCode.INVALID_QUERY);
        }

        Long nextCursor = reviewSlice.isEmpty() ? null :
                reviewSlice.getContent().getLast().getId();

        return ReviewConverter.toSlice(
                reviewSlice.map(ReviewConverter::toInfo).toList(),
                reviewSlice.hasNext(),
                nextCursor,
                reviewSlice.getSize()
        );
    }
}
