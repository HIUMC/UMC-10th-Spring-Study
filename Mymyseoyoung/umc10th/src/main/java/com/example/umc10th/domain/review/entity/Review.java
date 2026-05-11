package com.example.umc10th.domain.review.entity;


import com.example.umc10th.domain.common.base.BaseEntity;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "review")
public class Review extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review _id")
    private Long id;


    private Float rate;

    private String content;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    // Review 엔티티 내부
    @Builder
    public Review(String content, Float rate, Member member, Store store) {
        this.content = content;
        this.rate = rate;
        this.member = member;
        this.store = store;
    }



}
