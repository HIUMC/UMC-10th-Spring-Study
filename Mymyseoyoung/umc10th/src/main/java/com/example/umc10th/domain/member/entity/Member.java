package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.common.base.BaseEntity;
import com.example.umc10th.domain.region.enums.Address;
import com.example.umc10th.domain.member.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    private String password;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "address")
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(length = 15)
    private String phoneNumber;

    private boolean phoneVerified;

    private String profileUrl;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer point;
}

