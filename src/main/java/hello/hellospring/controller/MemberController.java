package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {
    //최초 스프링 생성 시 컨테이너에 컨트롤러 , 서비스등을 등록하고 가져다가 써야함.
    //private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    //Dependency Injection
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        System.out.println("회원가입");
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member =  new Member();
        member.setName(form.getName());
        //System.out.println("member = "+member.getName());
        memberService.join(member);

        return "redirect:/";   //회원가입이 끝나고 홈화면으로
    }

    //@RequestMapping(method = RequestMethod.POST, path = "/members/new2")
    @PostMapping("/members/new2")
    public String create2(MemberForm form){
        System.out.println("post test");
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";

    }
}
