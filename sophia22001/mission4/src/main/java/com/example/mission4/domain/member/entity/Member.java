package com.example.mission4.domain.member.entity;

import com.example.mission4.domain.member.entity.mapping.MemberFood;
import com.example.mission4.domain.member.entity.mapping.MemberTerm;
import com.example.mission4.domain.member.enums.Gender;
import com.example.mission4.domain.member.enums.SocialType;
import com.example.mission4.domain.mission.enums.Address;
import com.example.mission4.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
public class Member extends BaseEntity {

    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", nullable = false)
    @Builder.Default // @Builder를 사용한다면 필드에도 반드시 @Builder.Default를 붙여줘야한다.
    private String name = "미지정";

    @Column(nullable = false)
    @Builder.Default
    private String email = "미지정";

    // null 가능
    private String phoneNumber; // 스프링부트는 CamelCase를 snake_case로 자동 변환해준다.

    @Column(nullable = false)
    @Builder.Default
    private String profileUrl = "미지정";

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    // null 가능
    private LocalDate birth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Address address = Address.NONE;

    @Column(nullable = false)
    @Builder.Default
    private String detailAddress = "미지정";

    @Column(nullable = false)
    @Builder.Default
    private String socialUid = "미지정";

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SocialType socialType = SocialType.NONE;

    @Column(nullable = false)
    @Builder.Default
    private Long myPoint = 0L;

    // 실제로 member 테이블에 컬럼이 생기는 게 아님
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFood> memberFoodList = new ArrayList<>();
    // new 인 이유? 안하면 기본 초기값은 null인데, 여기서 값을 바로 추가할 수 없다.

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberTerm> memberTermList = new ArrayList<>();

}
