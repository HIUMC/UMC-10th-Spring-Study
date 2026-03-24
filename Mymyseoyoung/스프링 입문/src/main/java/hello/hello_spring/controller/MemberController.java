package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    //스프링 컨테이너에 컨트롤러 객체가 생성되어서 넣어짐 어노테이션을 통해 관리

    private final MemberService memberService;

    //스프링 컨테이너에 등록
    //스프링 컨테이너에 있는 애 갖다가 연결시켜줌

    @Autowired
    //DI ( 의존성 주입 ! )
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
