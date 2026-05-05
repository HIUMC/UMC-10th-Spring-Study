package com.example.umc10th.domain.region.entity;

import com.example.umc10th.domain.common.base.BaseEntity;
import com.example.umc10th.domain.region.enums.Address;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="region")
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="region_id")
    private Long id;

    //지역 이름
    @Enumerated(EnumType.STRING)
    private Address name;

}
