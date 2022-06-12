package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member fingById(long memberId) {
        return memberRepository.findById(memberId);
    }

    //Test 하기 위해 memberRepository 객체를 반환한다.
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
