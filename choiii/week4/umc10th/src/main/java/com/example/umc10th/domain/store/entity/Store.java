package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.food.entity.Food;
import com.example.umc10th.domain.region.entity.Region;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    private String address;
    private Float rating;

    @Column(name = "review_count")
    private Integer reviewCount;

    public void increaseReviewCount() {
        this.reviewCount = this.reviewCount == null ? 1 : this.reviewCount + 1;
    }
}
