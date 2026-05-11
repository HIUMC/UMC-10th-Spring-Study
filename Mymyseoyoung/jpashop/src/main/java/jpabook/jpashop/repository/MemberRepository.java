package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //entitymanager을 만들어서 여기에 주입해줌 .
    private final EntityManager em;
    /*기본적인 entity manager : persist 하면 영속성 컨텍스트에 멤버 객체를 넣음.

    그럼 나중에 트랜잭션이 commit 되는 시점에 db에 반영이 됨 !*/
    public void save (Member member) {
        //jpa가 얘를 저장 .
        em.persist(member);
    }
//단건 조회
    public Member findOne (Long id){
        return em.find(Member.class, id);
    }

    //전체 조회

    public List<Member> findAll()
    {
       /* List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        return result;
        --> 얘를 합친게 아래 return 값임 .
        */
        return em.createQuery("select m from Member m", Member.class).getResultList();

    }

    //이름으로 회원을 검색 : parameter : 이름임 .
    public List<Member> findByName (String name){
        return em.createQuery("select m from Member m where m.name =: name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }

}
