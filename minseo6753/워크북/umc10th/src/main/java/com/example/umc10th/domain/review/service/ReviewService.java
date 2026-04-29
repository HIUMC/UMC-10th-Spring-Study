package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReviewService {
    public ReviewResDTO.Info createReview(Long restaurantId, Integer star, String content, List<MultipartFile> files) {
        return null;
    }
}
