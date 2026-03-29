package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

///  @Controller 어노테이션을 붙이면
/// 코드를 실행할 때 해당 컨트롤러 객체를 생성하여
/// Spring Container의 Spring bean이 들고 있게 해준다
/// - Spring Container에서 Spring bean이 관리된다
/// Component Scan 방식
/// Spring bean에 등록할 때에는 Single Tone으로 관리한다 즉,
/// 유일하게 하나만 등록한고, 공유한다
@Controller
public class MemberController {
    /// DI 없이 아래처럼 객체를 만들어서 사용하면 안됨
    /// Spring Container가 만들어둔 객체를 주입받아 사용해야 함
    /// private final MemberService memberService = new MemberService();

    /// @Autowired 어노테이션 사용시 생성자를 이용해 외부에서 객체를 주입받을 때
    /// Spring Container에 있는 객체를 자동으로 연결시켜줌
    /// Autowired를 사용하려면 주입하려는 객체의 코드에도 조치를 취해주어야 함

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String members(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
