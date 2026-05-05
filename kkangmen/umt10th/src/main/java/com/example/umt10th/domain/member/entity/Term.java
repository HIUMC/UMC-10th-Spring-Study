package com.example.umt10th.domain.member.entity;

import com.example.umt10th.domain.member.enums.TermName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "term_name")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TermName termName = TermName.NONE;
}
