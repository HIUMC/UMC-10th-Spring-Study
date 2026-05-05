package com.example.umc10th.domain.notification.dto;

import lombok.Builder;

import java.util.List;

public class NotificationResDTO {

    @Builder
    public record NotificationListDTO(
            List<NotificationDetailDTO> notifications,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record NotificationDetailDTO(
            Long notificationId,
            String notificationType,
            String notificationComment,
            String createdAt
    ) {}
}
