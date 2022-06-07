package hello.core.member;

public interface MemberService {
    void join(Member member);

    Member fingById(long memberId);
}
