package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core", -> 컴포넌트 스캔을 할 지역 지정
//        basePackageClasses = AutoAppConfig.class, -> 컴포넌트 스캔을 할 클래스 시작 지정 -- basePackages 지정과 같음
        // 만약 basePackages를 지정하지 않으면 @ComponentScan을 선언한 곳을 베이스로 컴포넌트를 스캔한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemoryMemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
