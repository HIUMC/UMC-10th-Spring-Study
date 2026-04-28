package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.global.enums.Food;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "address")
    private String address;

    @Column(length = 15)
    private String phoneNumber;

    @Column(length = 200)
    private String profileUrl;

    @Column(name = "point")
    private Integer point;

    @Column(name = "food_preference")
    @Enumerated(EnumType.STRING)
    private Food foodPreference;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt")
    private LocalDate deletedAt;
}
