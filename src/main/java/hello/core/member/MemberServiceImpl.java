package hello.core.member;

public class MemberServiceImpl implements  MemberService{

    private final MemberRepository memberRepository; //추상화에만 의존


    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //DIP(의존 관계) 위반
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
