package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany // 실무에서는 절대 금지
    @JoinTable(
            name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne, OneToOne ( xToOne )은 기본 Fetch 값이 EAGER
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent") // OneToMany, ManyToMany ( xToMany )는 기본 Fetch값이 LAZY
    private List<Category> child = new ArrayList<>();

    public void addChild(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
