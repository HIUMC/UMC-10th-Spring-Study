package com.example.mission4.global.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 추상 클래스는
 * new로 직접 생성 불가능 -> 미완성 설계도
 * 여러 클래스들의 공통점을 뽑아 규격 정의
 * 반드시 자식 클래스에서 상속받아 완성해야 함
 */

@MappedSuperclass // 자식 클래스에게 부모 클래스가 가지는 칼럼만 매핑정보로 제공하고 싶다 선언
@EntityListeners(AuditingEntityListener.class) // 특정 이벤트(저장, 수정 등)가 발생했을 때, 이를 감지하여 자동으로 특정 작업을 수행
@Getter
public abstract class BaseEntity {

    @CreatedDate // 저장 이벤트가 발생했을때 해당 칼럼값을 현재 시각으로 저장
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // 수정 이벤트가 발생했을때 해당 칼럼값을 현재 시각으로 저장
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // null 가능
    private LocalDateTime deletedAt;
}
