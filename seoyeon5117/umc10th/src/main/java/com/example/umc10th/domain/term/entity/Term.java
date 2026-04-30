package com.example.umc10th.domain.term.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Table
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long termId;

    @Column(unique = true, name = "name")
    @NotNull
    @NotBlank
    private String termName;

    @Column(name = "content")
    @NotBlank
    private String termContent;

    @NotNull
    private Boolean isNecessary;

    @NotNull
    private Boolean isActive;
}
