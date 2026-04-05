package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core", // 어디서부터 Component를 찾을 지 지정해줄 수 있음
        // 만약 설정해주지 않으면 ComponentScan이 있는 패키지부터 탐색 시작
        // 권장하는 방식은 따로 지정해주지 않고 ComponentScan 설정 파일을 프로젝트 최상단 계층에 만드는 것
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // @ComponentScan을 사용하면 @Bean으로 명시적으로 등록하지 않아도 됨
}
