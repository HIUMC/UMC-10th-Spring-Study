package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

///  test 코드는 빌드 코드에 포함되지 않기 때문에
/// 알아보기 쉽게 그냥 한글로 작성해도 됨
class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long resultId = memberService.join(member);

        // then
        Assertions.assertThat(resultId).isEqualTo(member.getId());
    }

    @Test
    void 중복회원가입() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("hello");
        member2.setName("hello");

        // when
        Long resultId1 = memberService.join(member1);

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
        /*
        try {
            memberService.join(member2);
            fail();
        } catch(Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
        }
        */
    }

    @Test
    void 회원다찾기() {
    }

    @Test
    void 회원한명찾기() {
    }
}