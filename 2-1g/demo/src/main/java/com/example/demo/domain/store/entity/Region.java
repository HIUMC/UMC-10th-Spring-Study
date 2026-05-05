package com.example.demo.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "region_id")
    private Long id;

    @Column(nullable = false, name = "region_name", length = 50)
    private String name;
}
