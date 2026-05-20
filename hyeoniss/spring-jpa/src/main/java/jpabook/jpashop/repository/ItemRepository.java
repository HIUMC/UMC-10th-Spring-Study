package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {

        if (item.getId() == null) {
        } else {
            em.persist(item);
            em.merge(item); //변경 감지 merge 방법? 열심히 짠 코드를 jpa가 한줄로 바꿔버림..
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }
}