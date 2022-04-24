package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /**
     * 해당 서비스의 역할과 구현클래스를 구분 할 수 있게 수정함
     * MemoryMemberRepository의 경우 service 선언 부분에서 모두 사용하였지만,
     * 구현 클래스를 구분함으로 써 한곳만 수정해도 두군데 모두 수정한 것과 같은 효과를 가짐
     */

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    private FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }


}
