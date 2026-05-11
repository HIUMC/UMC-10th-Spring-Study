package com.example.umc10th.domain.usermission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;



public class UserMissionRequestDTO {

    public record getMemberInfo(
            @NotNull(message="멤버 아이디는 필수 항목입니다.")
            Long memberId,


            @NotNull(message = "페이지 번호는 필수 항목입니다.")
            @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
            Integer page
    ){}
}
