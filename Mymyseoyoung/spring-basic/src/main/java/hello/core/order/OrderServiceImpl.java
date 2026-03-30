package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository=new MemoryMemberRepository();
    //구체적인 코드를 의존하고 있음 .
   // private final DiscountPolicy discountPolicy=new FixDiscountPolicy();

    //private final DiscountPolicy discountPolicy=new RateDiscountPolicy();

    // DIP 위반 해결방법
    //인터페이스에만 의존함
    //-> NullPointException이 생김 !

    private DiscountPolicy discountPolicy;
    //해결방법 -> 누군가가 클라이언트인 OrderServiceImpl에  DiscountPolicy의 구현객체를
    //대신 생성하고 주입해주어야 함

    @Override
    public Order createOrder(Long memberId,String itemName,int itemPrice) {
        Member member=memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        //새로운 객체 만들어서 반환함
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}

