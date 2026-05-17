package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.ReviewWriteResDTO writeReview(Long storeId, ReviewReqDTO.ReviewWriteReqDTO request) {

        /*  요청으로 받은 이메일(userId)로 DB에서 회원을 조회, 없으면 MEMBER_NOT_FOUND 예외 */
        Member member = memberRepository.findByEmail(request.getUserId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        /* URL 경로에서 받은 storeId로 DB에서 가게를 조회 후 없으면 STORE_NOT_FOUND 예외 */
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));

        /* 조회한 member, store와 요청 데이터(rating, content)를 조합해서 Review 엔티티 객체 생성 */
        Review review = ReviewConverter.toReview(member, store, request);

        /* 만든 Review 객체를 DB에 저장 */
        Review saved = reviewRepository.save(review);

        return ReviewConverter.toReviewWriteResDTO(saved);
    }

    @Transactional(readOnly = true)
    public ReviewResDTO.ReviewPagination getMyReviews(Long memberId, String sort, String cursor, int size) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(0, size);
        Slice<Review> slice;

        if ("star".equals(sort)) {
            if (cursor == null) {
                slice = reviewRepository.findByMemberIdOrderByStarDesc(memberId, pageable);
            } else {
                String[] parts = cursor.split("_");
                BigDecimal star = new BigDecimal(parts[0]);
                Long cursorId = Long.parseLong(parts[1]);
                slice = reviewRepository.findByMemberIdCursorByStar(memberId, star, cursorId, pageable);
            }
        } else {
            if (cursor == null) {
                slice = reviewRepository.findByMember_IdOrderByIdDesc(memberId, pageable);
            } else {
                Long cursorId = Long.parseLong(cursor);
                slice = reviewRepository.findByMember_IdAndIdLessThanOrderByIdDesc(memberId, cursorId, pageable);
            }
        }

        List<ReviewResDTO.GetReview> data = slice.getContent().stream()
                .map(ReviewConverter::toGetReview)
                .collect(Collectors.toList());

        String nextCursor = null;
        if (slice.hasNext() && !data.isEmpty()) {
            Review last = slice.getContent().get(slice.getContent().size() - 1);
            nextCursor = "star".equals(sort)
                    ? last.getStar() + "_" + last.getId()
                    : String.valueOf(last.getId());
        }

        return ReviewConverter.toReviewPagination(data, slice.hasNext(), nextCursor, size);
    }
}
