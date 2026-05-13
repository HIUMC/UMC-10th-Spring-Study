package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor // C. 어노테이션 사용하는 방식
public class MemberRepository {

    // A. @PersistenceContext 사용하는 방식
//    // 엔티티 매니저 주입받기
//    @PersistenceContext
//    private EntityManager em;

    // B. @Autowired 사용하는 방식
//    // sping data jpa에서는 @PersistenceContext 대신 @Autowired만 써도 된다.
//    // -> @RequiredArgsConstructor 로 대체 가능
//    @Autowired
//    private EntityManager em;

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member); // 영속성 컨텍스트에 member 객체를 넣음. 트랜잭션이 commit되는 시점에 DB에 저장된다.
    }

    // 단건 조회
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    // 리스트 조회
    public List<Member> findAll() {
        // JPQL 작성해야한다.
        return em.createQuery("select m from Member m", Member.class) // em.createQuery(JPQL, 반환 타입)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        // JPQL 작성해야한다.
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

    }


}
