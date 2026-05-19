package com.example.umc10th.domain.member.dto;


import com.example.umc10th.domain.member.enums.FoodCategory;
import com.example.umc10th.domain.region.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class MemberRequestDTO {

    //마이페이지

    public record GetInfo(
            Long userId
    ){}


    //회원가입
    public record Join(

            @NotNull(message = "회원이름은 필수 항목입니다.")
            String name,

            @NotBlank(message = "이메일은 필수 항목입니다.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수 항목입니다.")
            @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
            String password,
            Gender gender,
            LocalDate birth,
            Address address,
            String phoneNumber,

            //선호 음식 선택
            List<FoodCategory> foodCategories


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
