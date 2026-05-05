package com.example.umc10th.domain.term.entity;

import com.example.umc10th.domain.memberterm.entity.MemberTerm;
import com.example.umc10th.global.apiPayload.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "term")
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "term")
    private List<MemberTerm> memberTerms = new ArrayList<>();

    @Column(unique = true, name = "name")
    @NotBlank
    private String name;

    @Column(name = "content")
    @NotBlank
    private String content;

    @NotNull
    @Column(name = "is_necessary")
    @Builder.Default
    private Boolean isNecessary = true;

    @NotNull
    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = false;
}
