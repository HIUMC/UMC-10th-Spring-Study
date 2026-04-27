package com.example.umc10th.domain.user.entity;

import com.example.umc10th.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title; // 알림 제목

    @Column(nullable = false)
    private String body;  // 알림 내용

    @Column(nullable = false)
    private Boolean isRead; // 읽음 여부 ( 기본값 false )

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 알림을 받는 유저 (N:1 관계)

    // 읽음 처리 메서드
    public void markAsRead() {
        this.isRead = true;
    }
}
