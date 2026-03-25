package hello_spring.repository;

import hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository(Spring jpa data)가 인터페이스에 대한 구현체를 만든뒤 Spring Bean에 등록함
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // select m from Member m where m.name = ? (규칙)
    @Override
    Optional<Member> findByName(String name);
}
