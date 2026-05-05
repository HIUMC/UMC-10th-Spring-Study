package com.example.demo.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(nullable = false, name = "store_name", length = 50)
    private String name;

    @Column(nullable = false, name = "store_address", length = 50)
    private String address;

    @Column(nullable = false, name = "store_point")
    private Float point;
}
