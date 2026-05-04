package com.example.demo.domain.review.entity;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @Column(nullable = false, name = "score")
    private Float score;

    @Column(nullable = false, name = "content", length = 255)
    private String content;

    @Column(nullable = false, name = "created_at")
    private Date createAt;

    @Column(name = "Field")
    private String field;
}
