package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//public 안해도됨 .다른곳에서 갖다쓰지 않으니 !
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트 끝나고 나면 데이터를 클리어해줘야 다시 돌렸을 때 결과가 제대로 나옴 .
    public void afterEach()
    {
         repository.clearStore();
    }
    @Test
    public void save()
    {
Member member = new Member();
member.setName("spring");

repository.save(member);

Member result = repository.findById(member.getId()).get();
assertThat(member).isEqualTo(result);
    }

    @Test
    public void findMyName()
    {
        Member member1= new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
