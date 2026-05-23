package com.example.mission4.domain.store.entity;

import com.example.mission4.domain.mission.entity.Location;
import com.example.mission4.domain.mission.enums.Address;
import com.example.mission4.global.common.enums.FoodName;
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
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Builder.Default
    private String name = "미지정";

    // 식당 종류 - 일식, 양식 ,,
    @Column(nullable = false)
    @Builder.Default
    private FoodName storeCategory = FoodName.NONE;

    @Column(nullable = false)
    @Builder.Default
    private Address address = Address.NONE;

    @Column(nullable = false)
    @Builder.Default
    private Long ownerNumber = 0L;

    /* 지역과의 단방향 매핑 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
}
