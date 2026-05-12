package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.store.enums.StoreCategory;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_name", nullable = false)
    @Builder.Default
    private String name = "이름없음";

    @Column(name = "store_address", nullable = false)
    @Builder.Default
    private String storeAddress = "주소가 없습니다.";

    @Column(name = "business_number", nullable = false)
    @Builder.Default
    private String businessNumber = "사업자등록안됨";

    @Column(name = "store_category", nullable = false)
    @Builder.Default
    private StoreCategory storeCategory = StoreCategory.NONE;

    @Column(name = "opne_hour", nullable = false)
    @Builder.Default
    private LocalTime openHour = LocalTime.of(0, 0);

    @Column(name = "close_hour", nullable = false)
    @Builder.Default
    private LocalTime closeHour = LocalTime.of(23, 59);

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<StoreImage> storeImageList = new ArrayList<>();
}
