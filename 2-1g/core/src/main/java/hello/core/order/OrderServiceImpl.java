package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
// 파이널이 붙은 멤버에 대한 생성자를 대신 만들어줌
public class OrderServiceImpl implements OrderService {


    //OrderServiceImpl이 인터페이스(추상) 뿐만 아니라 클래스(구현체)에도 의존하고 있음
    //따라서 이는 DIP 위반이고, 클래스를 변경할 때는 OCP를 위반하게 됨
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //DIP 원칙을 지키기 위해 인터페이스에만 의존하도록 변경
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;
    // final을 이용하면 생성자에서만 값을 수정하고 더 이상 바꾸지 못하게 할 수 있음

    // 필드주입, 필드에서 의존관계 바로 주입 - 권장x 테스트하기 어려움
    //@Autowired private DiscountPolicy discountPolicy;
    //@Autowired private MemberRepository memberRepository;

    //DIP, OCP 원칙 준수를 위한 생성자주입
    //구현체 변경이 필요한 경우 AppConfig에서만 수정하면 됨! - OCP
    //인터페이스에만 의존하고 구체적인 구현체에 대한 정보는 모름! - DIP

    // 생성자 주입, 생성자 호출 시점에 딱 한 번만 호출
    // 불변, 필수 의존관계에서 사용한다
    // @Autowired
    public OrderServiceImpl(/*@Qualifier("mainDiscountPolicy")*//*@MainDiscountPolicy*/DiscountPolicy DiscountPolicy, MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.discountPolicy = DiscountPolicy;
    }
    // 생성자가 하나만 있다면, Autowired 어노테이션을 생략해도 자동으로 주입이 된다

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // for Test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
