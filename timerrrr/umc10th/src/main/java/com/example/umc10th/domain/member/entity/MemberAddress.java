package com.example.umc10th.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_address")
public class MemberAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false)
    @Builder.Default
    private String address = "서울특별시 마포구 와우산로94 제2기숙사";  //주소 미입력시 본사? 주소로

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "town_name", nullable = false)
    @Builder.Default
    private String townName = "행정동 미입력";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
