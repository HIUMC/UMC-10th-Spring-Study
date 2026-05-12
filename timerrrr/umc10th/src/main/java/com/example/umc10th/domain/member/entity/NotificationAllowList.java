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
@Table(name = "notification_allow_list")
public class NotificationAllowList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_allow_new_event", nullable = false)
    @Builder.Default
    private Boolean isAllowNewEvent = false;

    @Column(name = "is_all_review_reply", nullable = false)
    @Builder.Default
    private Boolean isAllowReviewReply = false;

    @Column(name = "is_allow_inquiry_reply", nullable = false)
    @Builder.Default
    private Boolean isAllowInquiryReply = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
