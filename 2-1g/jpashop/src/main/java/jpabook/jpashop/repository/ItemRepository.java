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

        // item이 이미 등록되어 있으면 id가 있음
        // id가 없다는건 아예 새로운 item이라는 뜻
        // id가 있다는건 디비에 있던걸 가져왔다는 것
        // 기존의 것을 업데이트
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
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
