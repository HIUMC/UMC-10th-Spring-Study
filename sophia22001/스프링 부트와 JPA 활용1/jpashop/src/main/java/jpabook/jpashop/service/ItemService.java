package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // 변경 감지 -> merge보다 더 좋은 방식
    @Transactional
    void updateItem(Long itemId, Book param) {
        // itemParam: 파리미터로 넘어온 준영속 상태의 엔티티

        // findItem: 실제 DB에서 꺼내온 영속 상태의 엔티티
        Item findItem = itemRepository.findOne(itemId);

        // 영속 상태를 수정했으므로 쿼리가 날라간다.
        findItem.setName(param.getName());
        findItem.setPrice(param.getPrice());
        findItem.setStockQuantity(param.getStockQuantity());
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
