package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @Component가 붙은 클래스를 스프링 빈으로 등록한다.
        // @Configuration안에 @Component가 있기 때문에 스캔되지 않도록 제외시킨다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class

)
public class AutoAppConfig {

    // 수동 빈 등록 시 자동 빈 등록과 겹쳐서 CoreApplication에서 에러 발생
//    @Bean("memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
