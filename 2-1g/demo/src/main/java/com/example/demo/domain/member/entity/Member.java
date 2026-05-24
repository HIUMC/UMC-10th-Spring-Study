package com.example.demo.domain.member.entity;

import com.example.demo.domain.member.enums.Gender;
import com.example.demo.domain.member.enums.SocialType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false)
    private Long point;

    @Column(nullable = false)
    private Boolean agreement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialType socialLogin;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String socialId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 13)
    private String number;

    @Column(nullable = false, length = 10)
    private String nickname;

    @Column(nullable = false)
    private Long completionCount;

}
