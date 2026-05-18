package com.example.umc10th.domain.member.dto;


import com.example.umc10th.domain.region.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MemberRequestDTO {

    //마이페이지

    public record GetInfo(
            Long userId
    ){}


    //회원가입
    public record Join(
            String name,
            String email,
            Gender gender,
            LocalDate birth,
            Address address,
            String phoneNumber
    ){}

    public record getMyReviewRequest(
            @NotNull(message = "멤버 아이디는 필수 항목입니다.")
            Long memberId,

            String cursor,  // 커서 기반이라 추가 (null이면 첫 페이지)

            @NotNull(message = "정렬 기준은 필수 항목입니다.")
            String sortType,  // "id" 또는 "rating"

            @NotNull
            @Min(1)
            Integer pageSize
    ){}
}
