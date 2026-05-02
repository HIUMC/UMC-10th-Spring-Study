package com.example.mission4.domain.member.entity;

import com.example.mission4.domain.member.enums.Gender;
import com.example.mission4.domain.mission.enums.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter

/**
 * @Builder:
 * 엔티티(Member) 빌더의 용도: "DB에 저장할 때"
 * DTO(MemberResDTO) 빌더의 용도: "화면에 보여줄 때"
 */
@Builder

/**
 * @AllArgsConstructor:
 * @Builder를 클래스 레벨에 붙이면, 내부적으로 모든 필드를 포함하는 생성자가 필요함
 */
@AllArgsConstructor

/**
 * @NoArgsConstructor:
 * - JPA는 데이터베이스에서 조회한 데이터를 객체로 만들 때, 일단 기본 생성자로 객체를 생성한 뒤 값을 채워 넣는다.
 * - @Builder를 클래스 위에 붙이면 롬복이 모든 필드를 포함하는 생성자를 만드는데, 이 때문에 기본 생성자가 사라져 버린다.
 */
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성

@Table(name = "member")
public class Member {

    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    private String email;
    private Integer point;
    private String phoneNumber;
    private String profileUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Address address;

}
