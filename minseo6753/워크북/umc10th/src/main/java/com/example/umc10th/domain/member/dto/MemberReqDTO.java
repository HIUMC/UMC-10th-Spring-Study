package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record SignUp(
            String name,
            Gender gender,
            LocalDate birth,
            String siDo,
            String siGunGu,
            String eupMyeonDong,
            List<String> preference,
            Boolean agreeLocation,
            Boolean agreeMarketing
    ) {
    }

    public record Location(
            String eupMyeonDong
    ) {
    }
}
