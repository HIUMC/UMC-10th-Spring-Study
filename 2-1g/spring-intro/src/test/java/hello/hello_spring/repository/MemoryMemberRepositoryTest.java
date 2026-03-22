package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    /// 모든 Test는 모든 메서드가 의존 없이 독립적으로, 순서에 상관 없이 동작하도록 만들어야 함
    /// 따라서 Test가 끝날 때마다 repository를 비워주는 코드가 필요함
    /// 먼저 Test코드를 개발하고 그에 맞춰서 로직을 개발하는 것을 tdd(테스트 주도 개발)이라 함
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("leehan");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        /// 아래와 같이 그냥 출력해서 Test 해보는 방법도 있지만
        ///System.out.println(result == member);
        /// junit이 제공하는 Assertions를 사용해서 Test 해볼 수 있다
        /// 출력 창엔 아무것도 안뜨지만 좌측에 테스트 결과에 따라 표시가 뜨게 된다
        ///Assertions.assertEquals(member, result);

        /// assertj에서 제공하는 다음과 같은 방법도 있다
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    void findByName(){
        Member member1 = new Member();
        member1.setName("leehan1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("leehan2");
        repository.save(member2);

        Member result = repository.findByName("leehan1").get();
        Assertions.assertThat(member1).isEqualTo(result);
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("leehan1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("leehan2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
