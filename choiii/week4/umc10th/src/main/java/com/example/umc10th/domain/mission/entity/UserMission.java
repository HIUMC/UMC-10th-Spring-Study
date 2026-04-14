package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.common.enums.MissionStatus;
import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "user_mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserMission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus status;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    public void complete(String verificationCode, LocalDateTime completedAt) {
        this.status = MissionStatus.COMPLETE;
        this.verificationCode = verificationCode;
        this.completedAt = completedAt;
    }
}
