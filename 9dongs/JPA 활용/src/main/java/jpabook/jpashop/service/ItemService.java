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
        itemRepository.save(item); // merge 호출 (준영속 상태의 엔티티를 영속 상태로 변경할 때 사용하는 기능)
    }

    // 변경 감지 기능 활용해 준영속성 엔티티 관리
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        // 트랜잭셔널 커밋 -> JPA flush() -> 영속성 컨텍스트 중 변경 확인 -> 업데이트
    }
    // 단발성 업데이트 금지.
    // setter말고 findItem.change 같은 의미있는 method 만들어야함.

    // merge의 기능과 완전히 동일한 코드
    // 객체를 파라미터로 받아 다 업데에이트
    // 주의점 : merge는 모든 필드를 업데이트 한다. -> null로 업데이트 될수도 있음
    // -> 실무에서는 엔티티 변경시에 Merge 보다 변경 감지를 사용.
//    @Transactional
//    public Item updateItem(Long itemId, Book param) {
//        Item findItem = itemRepository.findOne(itemId);
//        findItem.setName(param.getName());
//        findItem.setPrice(param.getPrice());
//        findItem.setStockQuantity(param.getStockQuantity());
//        return findItem;
//    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
