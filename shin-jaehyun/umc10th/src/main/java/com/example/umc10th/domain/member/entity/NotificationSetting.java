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
@Table(name = "notification_setting")
public class NotificationSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "new_event_alert", nullable = false)
    private Boolean newEventAlert;

    @Column(name = "review_reply_alert", nullable = false)
    private Boolean reviewReplyAlert;

    @Column(name = "inquiry_reply_alert", nullable = false)
    private Boolean inquiryReplyAlert;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
