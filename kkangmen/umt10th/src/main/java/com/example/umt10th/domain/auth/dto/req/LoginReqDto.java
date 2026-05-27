package com.example.umt10th.domain.auth.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class LoginReqDto {

    @Builder
    public record LoginReq(

            @NotBlank(message = "아이디(이메일)는 필수입니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            String password
    ){}
}
