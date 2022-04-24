package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {


    /**
     * 해당 서비스를 변경하는 순간 클라이언트 코드를 변경해야한다
     * 해당 서비스를 사용하기 위해서 인터페이스(DiscountPolicy) 뿐 아니라
     * 구체클래스(new RateDiscountPolicy())도 의존 하고 있다
     */
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     * 이와 같은 방식으로 사용하면 해당 클라이언트 코드에서는 구현체를 변경해주지 않아도 여러가지의 구현 클래스를 사용가능하다
     * 하지만 해당인터페이스의 구현클래스를 할당해주는 외부의 무언가가 필요하다
     * IOC(제어의 역전) - 사용하는 서비스가 아닌 외부의 무언가가 제어하는 것
     * DIP(의존관계 역전원칙) - 구현클래스가 아닌 인터페이스를 의존하는 것
     * - 객체지향의 5원칙 (SOLID) 중에 하나로 해당 원칙과  OCD 원칙을 지키기 위해서 spring를 사용함
     */

    private final MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
