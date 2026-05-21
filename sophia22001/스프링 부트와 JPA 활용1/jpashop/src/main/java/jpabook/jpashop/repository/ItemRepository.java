package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null){ // id가 없는 새로운 item
            em.persist(item);
        } else {
            em.merge(item); // 업데이트와 비슷함
            // merge: 준영속 상태의 엔티티를 영속 상태로 변경할 때 사용. 모든 필드들 대체함
            // 병합 시 값이 없으면 null로 업데이트되어서 위험하다 !!
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
