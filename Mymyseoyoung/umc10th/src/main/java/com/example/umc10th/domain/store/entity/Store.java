package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.common.base.BaseEntity;
import com.example.umc10th.domain.region.entity.Region;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="store_id")
    private Long id;

    //가게 이름
    @Column(name="name",nullable=false,length = 50)
    private String name;


    private String phone;

    //상세 주소
    private String detailAddress;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="region_id")
    private Region region;





}
