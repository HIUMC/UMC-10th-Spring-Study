package hello.hello_spring.domain;

public class Member {

    private Long id;
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
