package hello.hello_spring.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "email")
    private String email;

    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "point")
    private Integer point;

    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "profile_url")
    private String profileUrl;

    public Member(String name) {
        this.name = name;
    }
}
