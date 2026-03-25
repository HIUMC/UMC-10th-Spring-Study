package hello_spring.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)// PK, 자동 생성되는 id
    private Long id;

    private String name;

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
