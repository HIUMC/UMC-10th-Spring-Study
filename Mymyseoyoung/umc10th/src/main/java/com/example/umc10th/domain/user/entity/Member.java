package com.example.umc10th.domain.user.entity;

import com.example.umc10th.common.base.BaseEntity;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.user.enums.Gender;
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
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

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

    private String profileUrl;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer point;
}

