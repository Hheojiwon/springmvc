package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements  OrderService{
    //주문 생성 요청이 오면?
    private final MemberRepository memberRepository=new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy=new FixDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member=memberRepository.findById(memberId); //회원정보 조회
        int discountPrice=discountPolicy.discount(member,itemPrice); //member 넘김
        //최종적으로 할인된 가격을 받음
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
