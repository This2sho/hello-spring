package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

//    @Autowired // setter 주입 방법(DI) : 단점 final이 아니기 때문에 바뀔 위험이 있음.
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }


    @Autowired // 생성자에 이게 있으면 스프링 컨테이너에 있는 MemberService를 가져다 연결시켜줌
    // 생성자 주입 방법(DI) 그냥 이방식대로 해야함!!
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
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
