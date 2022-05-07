package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * 해당 어노테이션을 사용하지 않으면 싱글턴 컨테이너가 보장되지 않는다 (CGLIB)
 * impl단에서 new를 사용해서 서비스를 선언한 것과 같은 기능이며
 * 이는 스프링 컨테이너에서 관리하는 객체가 아니므로 DI를 위반한다
 */
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /**
     * 해당 서비스의 역할과 구현클래스를 구분 할 수 있게 수정함
     * MemoryMemberRepository의 경우 service 선언 부분에서 모두 사용하였지만,
     * 구현 클래스를 구분함으로 써 한곳만 수정해도 두군데 모두 수정한 것과 같은 효과를 가짐
     */

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }


}
