package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm()
    {
        return "members/createMemberForm";
    }

    @PostMapping ("/members/new")
    public String create(MemberForm memberForm)
    {
        Member member = new Member();
        member.setName(memberForm.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model)
    {
        List<Member> members= memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }
}
