package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.web.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "members/createMemberForm";
        }

        Address address = Address.builder()
                .city(form.getCity())
                .street(form.getStreet())
                .zipcode(form.getZipcode())
                .build();

        Member member = Member.builder()
                .name(form.getName())
                .address(address)
                .build();

        memberService.join(member);

        return "redirect:/";
    }

    /**
     * 전체 멤버 조회
     */
    @GetMapping("/members")
    public String findAllMember(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
