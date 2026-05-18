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
            // 준영속 엔티티의 식별자 값으로 1차캐시에서 엔티티 조회
            // 만약 1차캐시에 없으면 디비에서 엔티티 조회하고 1차캐시에 저장
            // 조회한 영속 엔티티에 파라미터로 넘어온 엔티티의 값을 밀어넣음(업데이트)

            // 더티체킹을 하면 필요한 값들만 변경되지만 병합을 사용하면 모든 속성이 변경됨
            // 머지의 경우 파라미터로 넘어온 엔티티에 속성값이 없는 필드의 경우 null로 업데이트될 수 있음
            // 따라서 위험함

            // 따라서 넘어온 파라미터(준영속 엔티티)를 머지하는 방식이 아닌
            // 넘어온 파라미터에서 id를 뽑아내고 그 식별자 값으로 영속 엔티티를 조회하여
            // 조회한 영속 엔티티를 수정하는 방식으로 디비 업데이트하기 (더티체킹)
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
