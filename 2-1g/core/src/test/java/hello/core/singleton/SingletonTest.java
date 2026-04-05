package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2 --> 사용자가 많아지면 비효율, 메모리 낭비 --> 싱글톤으로 관리해야함
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singleTonServiceTest() {
        // SingletonService는 생성자를 private으로 막아놨기 때문에 new로 생성하지 못함
        // SingletonService singletonService = new SingletonService();

        // SingletonService는 싱글톤으로 관리하기 때문에 두 개를 만들어도 같은 객체를 참조함
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 둘이 같은 객체!
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

        // isSameAs --> ==
        // isEqualTo --> equals 메서드

        // 그렇다면 모든 객체 클래스 코드를 싱글톤으로 수정? ㄴㄴ
        // 스프링 컨테이너를 쓰면 기본적으로 싱글톤으로 관리해줌
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        // 스프링 컨테이너에 등록된 빈은 자동으로 싱글톤으로 관리된다
        // 싱글톤의 단점은 지우고, 장점만 살려주는 스프링 컨테이너
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 둘이 같은 객체!
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
