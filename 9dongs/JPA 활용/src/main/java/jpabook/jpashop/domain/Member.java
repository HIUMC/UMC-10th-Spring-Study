package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장 타입 사용 표기
    private Address address;

    @OneToMany(mappedBy = "member") // order table 속 member 필드에 의해 "맵핑됨"을 표기 ( 읽기 전용 )
    private List<Order> orders = new ArrayList<>();
}
