package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.web.BookForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @GetMapping("/items/new")
    public String createForm(Model model){

        model.addAttribute("form", BookForm.builder().build());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form){

        Book book = Book.builder()
                .name(form.getName())
                .price(form.getPrice())
                .stockQuantity(form.getStockQuantity())
                .author(form.getAuthor())
                .isbn(form.getIsbn())
                .build();

        itemService.saveItem(book);
        return "redirect:/items";
    }

    /**
     * 전체 상품 목록 조회
     */
    @GetMapping("/items")
    public String items(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);

        return "items/itemList";
    }

    /**
     * 상품 수정 폼
     */
    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model){
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = BookForm.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .author(item.getAuthor())
                .isbn(item.getIsbn())
                .build();

        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    /**
     * 상품 수정
     */
    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute BookForm form){

        itemService.updateItem(itemId, form.getName());

        return "redirect:/items";
    }
}
