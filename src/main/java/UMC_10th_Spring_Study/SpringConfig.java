package UMC_10th_Spring_Study;

import UMC_10th_Spring_Study.domain.Member;
import UMC_10th_Spring_Study.repository.MemberRepository;
import UMC_10th_Spring_Study.repository.MemoryMemberRepository;
import UMC_10th_Spring_Study.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();

    }
}
