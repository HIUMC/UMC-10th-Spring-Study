package umc10th.assignment.user.dto;

import lombok.Builder;

public class UserResponseDto {
    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {
    }

    @Builder
    public record UpdateInfo(
            String name,
            String profileUrl,
            String phoneNumber
    ) {
    }

    @Builder
    public record GetPoint(
            Integer point
    ) {
    }
}
