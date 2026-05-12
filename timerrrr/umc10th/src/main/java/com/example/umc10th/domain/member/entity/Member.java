package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Builder.Default
    private String name = "이름없음";

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth_date", nullable = false)
    @Builder.Default
    private LocalDate birthDate =  LocalDate.now();

    @Column(name = "nickname", nullable = false)
    @Builder.Default
    private String nickname = null;

    @Column(name = "email", nullable = false)
    @Builder.Default
    private String email = "이메일없음";

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "current_mission_count", nullable = false)
    @Builder.Default
    private Integer currentMissionCount = 0;

    @Column(name = "social_provider", nullable = false)
    @Builder.Default
    private SocialType socialProvider = SocialType.NONE;

    @Column(name = "social_uid",  nullable = false)
    @Builder.Default
    private String socialUid = "000000000000";

    //연관관계 매핑

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberAddress> memberAddressList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.REMOVE)
    private NotificationAllowList notificationAllowList = new NotificationAllowList();

    @PrePersist
    protected void onCreate() {    //닉네임 미입력시 자동으로 생성
        if (this.nickname == null) {
            this.nickname = "user_" + UUID.randomUUID().toString().substring(0, 8);  //user_+uid 앞 8자리 형식
        }
    }

}
