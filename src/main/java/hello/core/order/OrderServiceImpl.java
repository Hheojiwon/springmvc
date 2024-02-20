package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements  OrderService{ //기능 실행하는 책임만 짐
    //주문 생성 요청이 오면?
    private final MemberRepository memberRepository; //fianl 로 되있으면 기본적으로 생성자 할당 되어야 함
    private final DiscountPolicy discountPolicy; //인터페이스에만 의존 (구제화에 의존 X)

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member=memberRepository.findById(memberId); //회원정보 조회
        int discountPrice=discountPolicy.discount(member,itemPrice); //member 넘김
        //최종적으로 할인된 가격을 받음
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

