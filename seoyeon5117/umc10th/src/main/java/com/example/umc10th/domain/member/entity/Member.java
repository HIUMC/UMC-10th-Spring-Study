package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.foodpreference.entity.FoodPreference;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.memberterm.entity.MemberTerm;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.reviewreply.entity.ReviewReply;
import com.example.umc10th.global.apiPayload.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @NotNull
    @Builder.Default
    private Gender gender = Gender.NOT_SPECIFIED;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "address")
    private String address;

    @Column(length = 15)
    private String phoneNumber;

    @Column(length = 200)
    private String profileUrl;

    @Column(name = "point")
    @NotNull
    @Builder.Default
    private Integer point = 0;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<FoodPreference> foodPreferences = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberTerm> memberTerms = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<ReviewReply> replies = new ArrayList<>();
}
