package com.example.umc10th.domain.home.dto;

import lombok.Builder;

import java.util.List;

public class HomeReqDTO {

    public record HomeRequest(
            Long memberId,
            Long locateId,
            Long cursor,
            Integer size
    ) {
    }
}
