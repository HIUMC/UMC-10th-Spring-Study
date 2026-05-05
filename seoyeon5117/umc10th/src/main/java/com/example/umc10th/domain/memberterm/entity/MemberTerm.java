package com.example.umc10th.domain.memberterm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_term")
public class MemberTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "term_id", nullable = false)
    private Long termId;

    @Column(name = "is_agreed", nullable = false)
    private Boolean isAgreed;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
