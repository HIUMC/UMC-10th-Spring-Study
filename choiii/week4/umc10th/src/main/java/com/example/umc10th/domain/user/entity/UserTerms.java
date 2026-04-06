package com.example.umc10th.domain.user.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "user_terms")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserTerms {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_terms_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "required_agreed")
    private Boolean requiredAgreed;

    @Column(name = "location_agreed")
    private Boolean locationAgreed;

    @Column(name = "marketing_agreed")
    private Boolean marketingAgreed;

    @Column(name = "agreed_at")
    private LocalDateTime agreedAt;
}
