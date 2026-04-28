package hello.core.xml;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

// bean 설정은 Configuration파일 뿐만 아니라 xml파일로도 가능하다
public class XmlAppContext {

    @Test
    void xmlAppContext() {
        // XML 설정 테스트는 잠시 사용하지 않음
        // ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        // MemberService memberService = ac.getBean("memberService", MemberService.class);
        // Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
