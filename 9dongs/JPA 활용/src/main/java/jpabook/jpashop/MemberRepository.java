package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext // 스프링 부트가 엔티티 매니저 다 만들어줌
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // 멤버를 리턴 안하는 이유 : 커멘드와 쿼리를 분리
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
