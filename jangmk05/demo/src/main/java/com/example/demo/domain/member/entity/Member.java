package com.example.demo.domain.member.entity;

import com.example.demo.domain.member.enums.Gender;
import com.example.demo.domain.member.enums.Preference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "address")
    private String address;

    @ElementCollection(targetClass = Preference.class)
    @CollectionTable(
            name = "member_preference",
            joinColumns = @JoinColumn(name = "member_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "preferences")
    private Set<Preference> preferences;

    @Column(name = "agreement")
    private boolean agreement;
}
