package io.torres.ddunddun.controller;

import io.torres.ddunddun.service.MemberService;
import io.torres.ddunddun.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("member", new MemberVo());
        return "member/signupForm";
    }

    @PostMapping("/signup")
    public String signup(MemberVo memberVo) {
        memberService.signUp(memberVo);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "/member/loginForm";
    }

}
