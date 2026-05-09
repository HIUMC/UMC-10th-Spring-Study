package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {


    public record storeReview
            (
                    String title,
                    String context,
                    Long star

            ){

    }


}
