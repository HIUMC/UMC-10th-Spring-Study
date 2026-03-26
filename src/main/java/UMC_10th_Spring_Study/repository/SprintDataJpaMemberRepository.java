package UMC_10th_Spring_Study.repository;

import UMC_10th_Spring_Study.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SprintDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    @Override
    Optional<Member> findByName(String name);
}
