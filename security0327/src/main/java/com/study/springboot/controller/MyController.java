package com.study.springboot.controller;

import com.study.springboot.dto.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes("member")
//@SessionAttributes("member")는 member라는 이름을 가진 모델(Model) 속성을 세션에 저장하겠다는 의미
// 이렇게 저장된 데이터는 해당 컨트롤러(Controller)에서만 사용할 수 있음.
@Controller
public class MyController {

    @ModelAttribute("member")
    public Member setMember() {
        return new Member();
    }

    @GetMapping("/")
    @ResponseBody
    public Member root(@ModelAttribute("member") Member member) {
        if (member.getMid() == null) {
            member.setId(1L);
            member.setMid("test");
            member.setPass("1234");
            return null;
        }

        return member;
    }

    @GetMapping("/admin")
    String admin() {
        return "admin/admin";
    }

    @GetMapping("/member")
    String member() {
        return "member/member";
    }

    @GetMapping("/guest")
    String guest(@ModelAttribute("member") Member member, Model model) {
        if (member.getMid() == null) {
            return "redirect:/login";
        }
        model.addAttribute("member", member);
        return "guest/guest";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }
}
