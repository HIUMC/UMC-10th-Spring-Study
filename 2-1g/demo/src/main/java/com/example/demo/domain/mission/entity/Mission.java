package com.example.demo.domain.mission.entity;

import com.example.demo.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "store_id")
    private Store store;

    @Column(nullable = false, name = "mission_description")
    private String description;

    @Column(nullable = false, name = "point")
    private Long point;

    @Column(nullable = false, name = "deadline")
    private Long deadline;
}
