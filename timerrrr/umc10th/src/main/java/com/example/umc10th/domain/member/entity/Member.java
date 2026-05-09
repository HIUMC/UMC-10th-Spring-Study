package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    private Long id;
    private String name;
    private Gender gender;
    private LocalDate birthDate;
    private String nickname;
    private String email;
    private String phoneNumber;
    private Integer point;
    private Integer currentMissionCount;


}
