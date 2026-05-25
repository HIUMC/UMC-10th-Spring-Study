package com.example.umt10th.domain.member.entity;

import com.example.umt10th.domain.member.enums.Address;
import com.example.umt10th.domain.member.enums.Gender;
import com.example.umt10th.domain.member.enums.SocialType;
import com.example.umt10th.global.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;

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

    @Column(name = "name", nullable = false, length = 5)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Address address = Address.NONE;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @Column(name = "social_uid")
    private String socialUid;

    @Column(name = "social_type")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SocialType socialType = SocialType.NONE;

    @Column(name = "point")
    private Integer point = 0;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;
}
