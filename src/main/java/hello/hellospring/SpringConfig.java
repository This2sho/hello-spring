package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Java 코드로 직접 Spring Bean 등록하는 방법
// DB가 정해지지 않거나 바뀔 때 Component Scan을 사용할 때는 코드들을 다 수정해줘야하지만
// 이렇게 직접 Config를 쓸 때는 memberRepository부분만 바꿔주면 되는 장점이 있음.
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
