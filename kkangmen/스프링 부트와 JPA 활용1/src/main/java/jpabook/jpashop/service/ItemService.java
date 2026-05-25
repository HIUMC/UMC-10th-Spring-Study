package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    /***
     * 영속성 컨텍스트가 TX이 커밋될 때 DirtyChecking을 통해 DB 업데이트
     */
    public void updateItem(Long itemId, String name) {
        Item item = itemRepository.findOne(itemId); // 영속 상태인 객체를 꺼낸다.
        item.setName(name);
    }
}
