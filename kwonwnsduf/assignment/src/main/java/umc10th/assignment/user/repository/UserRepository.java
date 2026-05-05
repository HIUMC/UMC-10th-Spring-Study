package umc10th.assignment.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc10th.assignment.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
