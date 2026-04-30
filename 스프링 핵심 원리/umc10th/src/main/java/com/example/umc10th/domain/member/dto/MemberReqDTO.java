package com.example.umc10th.domain.member.dto;
import lombok.Getter;
public class MemberReqDTO {
    @Getter
    public static class JoinDTO {
        private String name;
        private String gender;
        private String birthDate;
        private String address;
        private String preferredFoodIds;
    }
}
