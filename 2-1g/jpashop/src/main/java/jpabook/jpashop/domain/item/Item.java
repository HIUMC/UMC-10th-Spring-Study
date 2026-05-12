package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    // 실무에서는 ManyToMany 사용하지 말 것
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // 재고 더하기
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    // 재고 줄이기
    public void removeStock(int quantity) {
        if (stockQuantity >= quantity) {
            this.stockQuantity -= quantity;
        } else {
            throw new NotEnoughStockException("need more stock");
        }
    }
}
