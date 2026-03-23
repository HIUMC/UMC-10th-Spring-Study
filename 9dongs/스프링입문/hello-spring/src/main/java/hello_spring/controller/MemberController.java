package hello_spring.controller;

import hello_spring.domain.Member;
import hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // 생성자 제거 후 @Autowired private MemberService memberService; => 필드 주입
    private final MemberService memberService;

    /*
    - 세터 주입 : setter 후 AutoWired
        - 멤버 컨트롤러 호출할 때 public으로 열려있어야함. => 단점임.
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
     */

    // 생성자 주입 ( Best Case )
    @Autowired // memberService 연결 시켜줌
    public MemberController(MemberService memberService) { // DI
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
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
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
