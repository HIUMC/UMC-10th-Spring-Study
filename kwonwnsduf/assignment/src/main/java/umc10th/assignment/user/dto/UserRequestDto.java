package umc10th.assignment.user.dto;

public class UserRequestDto {
    // 마이페이지 조회
    public record GetInfo(
            Long id
    ) {
    }

    // 내 정보 수정
    public record UpdateInfo(
            String name,
            String profileUrl,
            String phoneNumber
    ) {
    }
}
