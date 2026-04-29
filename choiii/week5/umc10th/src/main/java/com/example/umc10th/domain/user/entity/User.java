package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.common.enums.Address;
import com.example.umc10th.domain.common.enums.Gender;
import com.example.umc10th.domain.common.enums.SocialProvider;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Address address;

    @Column(nullable = false)
    private Integer point;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_provider", nullable = false, length = 20)
    private SocialProvider socialProvider;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void addPoint(int amount) {
        this.point += amount;
    }
}
