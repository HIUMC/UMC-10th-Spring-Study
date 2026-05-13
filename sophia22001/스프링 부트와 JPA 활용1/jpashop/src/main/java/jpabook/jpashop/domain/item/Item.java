package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
/**
 * public enum InheritanceType {

 *      SINGLE_TABLE, // 한 테이블에 다 만들겠다.
 *      TABLE_PER_CLASS, // Book, Album, Movie 테이블을 따로 만들겠다.
 *      JOINED
 *}
 */

@DiscriminatorColumn(name = "dtype") // 한 테이블내에서 엔티티마다 구분할 수 있는 방법 정의 // 구분하는 곳에서 @DiscriminatorValue("B") 처럼 사용한다.
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //== 비지니스 로직 ==

    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
