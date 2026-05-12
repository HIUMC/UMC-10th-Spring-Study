package umc10th.assignment.usermission.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import umc10th.assignment.usermission.entity.MissionStatus;

import umc10th.assignment.usermission.entity.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
        select um
        from UserMission um
        join fetch um.mission m
        join fetch m.store s
        where um.user.memberId = :memberId
          and um.status = :status
        """)
    Page<UserMission> findUserMissionsByStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}
