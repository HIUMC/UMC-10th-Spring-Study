package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ApplicationContextBasicFindTest {

    //컨테이너에서 원하는 객체를 잘 가져오는지 확인하는 테스트
    AnnotationConfigApplicationContext ac = new
            AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        //스프링 컨테이너(ac)에게 "이름은 memberService이고, 타입은 MemberService인 애를 찾으라고 요청
        MemberService memberService = ac.getBean("memberService",
                MemberService.class);
        //멤버 서비스 :인터페이스고 ,
        // MemberServiceImpl의 인스턴스이면 성공 !
        //꺼내온 객체가 memberServiceImpl 클래스의 객체가 맞는지 검증
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("이름 없이 타입만으로 조회")

    void findBeanByType() {
        //이름은 상관없으니 MemberService 타입인 객체를 찾아줘
        //같은 타입의 객체가 컨테이너에 2개 이상 등록되어 있다면 오류
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService",
                MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        //ac.getBean("xxxxx", MemberService.class);
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () ->
                ac.getBean("xxxxx", MemberService.class));
    }

}
