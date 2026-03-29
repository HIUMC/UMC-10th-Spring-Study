package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {


    //OrderServiceImpl이 인터페이스(추상) 뿐만 아니라 클래스(구현체)에도 의존하고 있음
    //따라서 이는 DIP 위반이고, 클래스를 변경할 때는 OCP를 위반하게 됨
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //DIP 원칙을 지키기 위해 인터페이스에만 의존하도록 변경
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    //DIP, OCP 원칙 준수를 위한 생성자주입
    //구현체 변경이 필요한 경우 AppConfig에서만 수정하면 됨! - OCP
    //인터페이스에만 의존하고 구체적인 구현체에 대한 정보는 모름! - DIP
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
