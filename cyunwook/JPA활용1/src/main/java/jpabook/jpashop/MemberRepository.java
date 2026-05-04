package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;
    public Long save (Member member) {
        em.persist(member);
        return member.getId();//id만 반환하는이유: command성이라 return값을 최대한 간략하게
    }
    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
