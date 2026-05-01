package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        // AppConfig에서
        // memberService -> new memberRepository 호출
        // orderService -> new memberRepository 호출
        // 객체가 여러번 생성되나 ?? 아니다. 스프링이 싱글톤을 보장한다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository",MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // 참조가 같다
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass()); // hello.core.AppConfig$$SpringCGLIB$$0
        // CGLIB:
        // 내가 만든 클래스 X. @Configuration 이 해주는 기능
        // CGLIB 라는 바이드 코드 조작 라이브러리로 조작한 클래스를 만들어 이걸 스프링 빈으로 등록시킨다.
        // 이미 그 스프링 빈이 있으면 있는 걸 반환하고, 없으면 새롭게 스프링 빈을 생성한다. -> 싱글톤 보장!
    }
}
