package hello_spring.repository;

import hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // Optional : Null이 그대로 반환 안되고 Optional로 감싸서 반환됨.
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
