package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Member member = new Member("spring1");

        // when
        Member findMember = memberRepository.save(member);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    void findByName() {
        // given
        Member member1 = new Member("spring1");
        Member member2 = new Member("spring2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        Member member = memberRepository.findByName("spring1").get();

        // then
        Assertions.assertThat(member).isEqualTo(member1);
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member("spring1");
        Member member2 = new Member("spring2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();

        // then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}