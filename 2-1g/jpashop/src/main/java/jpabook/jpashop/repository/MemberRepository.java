package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RequiredArgsConstructor // final 키워드가 달린 객체 생성자 자동생성
public class MemberRepository {

    //private final EntityManager em;

    // jpa의 엔티티 매니저를 주입해줌 - @PersistenceContext
    @PersistenceContext // -> SpringBoot가 Autowired도 똑같이 동작하게 해줌
    private EntityManager em;

    // Member 저장
    public void save(Member member) {
        em.persist(member);
    }

    // id로 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // 전체 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    // 이름으로 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
