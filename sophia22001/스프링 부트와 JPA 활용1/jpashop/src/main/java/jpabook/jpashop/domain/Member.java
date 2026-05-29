package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장 타입 매핑. @Embedded or @Embeddable 둘 중에 하나만 해놔도 된다.
    private Address address;

    @OneToMany(mappedBy = "member") // mappedBy: 난 연관관계의 주인이 아니고, 주인에 의해 매핑된 거울이다.
    private List<Order> orders = new ArrayList<>();
}
