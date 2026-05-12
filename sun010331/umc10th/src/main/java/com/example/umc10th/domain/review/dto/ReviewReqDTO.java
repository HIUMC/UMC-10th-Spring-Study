package com.example.umc10th.domain.review.dto;

import java.math.BigDecimal;

public class ReviewReqDTO {


    public record storeReview
            (
                    String title,
                    String content,
                    Float star

            ){

    }


}
