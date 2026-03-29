package hello.hello_spring.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    // DB에서 ID를 자동으로 생산하는 전략 - IDENTITY 전략
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(name = "username")
    private String name;

    // getter/setter - control+enter로 쉽게 생성 가능
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
