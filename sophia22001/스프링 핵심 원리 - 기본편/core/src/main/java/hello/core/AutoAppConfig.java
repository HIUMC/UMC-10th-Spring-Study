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
        basePackages = "hello.core.member", // 이 문자열의 패키지 기준으로 탐색
        basePackageClasses = AutoAppConfig.class // 이 클래스가 위치한 패키지를 기준으로 탐색

        // basePackages와 basePackageClasses 둘다 설정하면, 두 범위가 함쳐진 범위로 적용된다.

)
public class AutoAppConfig {

    // 수동 빈 등록 시 자동 빈 등록과 겹쳐서 CoreApplication에서 에러 발생
//    @Bean("memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
