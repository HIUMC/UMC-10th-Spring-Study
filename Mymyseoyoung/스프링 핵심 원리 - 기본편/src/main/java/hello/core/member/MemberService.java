package hello.core.member;

public interface MemberService {
    //회원가입,회원 조회 기능 필요함

    //가입
    void join(Member member);


    //조회
    Member findMember(Long memberId);

}
