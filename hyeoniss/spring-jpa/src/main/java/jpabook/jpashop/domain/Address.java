package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable //내장 타입
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { //변경 불가하게... embeded는 protected로
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

//이번주 커밋이 저번 주 pr에 병합된 것 같습니다...