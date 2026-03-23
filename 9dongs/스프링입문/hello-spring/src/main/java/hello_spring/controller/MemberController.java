package hello_spring.controller;

import hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // memberService 연결 시켜줌
    public MemberController(MemberService memberService) { // DI
        this.memberService = memberService;
    }
}
