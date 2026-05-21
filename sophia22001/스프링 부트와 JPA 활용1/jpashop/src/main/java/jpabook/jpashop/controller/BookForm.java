package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

// 상품 등록 폼
@Getter @Setter
public class BookForm {

    private Long id;
    private String name;
    private Integer price;
    private Integer stockQuantity;
    private String author;
    private String isbn;

}
