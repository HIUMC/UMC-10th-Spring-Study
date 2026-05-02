package com.example.mission4.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "food")
public class Food {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private com.example.mission4.domain.member.enums.Food name;


}
