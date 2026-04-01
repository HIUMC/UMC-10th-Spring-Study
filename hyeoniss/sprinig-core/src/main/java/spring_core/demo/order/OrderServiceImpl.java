package spring_core.demo.order;

import spring_core.demo.discount.DiscountPolicy;
import spring_core.demo.discount.FixDiscountPolicy;
import spring_core.demo.member.Member;
import spring_core.demo.member.MemberRepository;
import spring_core.demo.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


}
