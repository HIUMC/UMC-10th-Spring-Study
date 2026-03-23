package spring.spring_start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.spring_start.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
