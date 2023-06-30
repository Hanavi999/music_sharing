package com.example.music_sharing.controller;

import com.example.music_sharing.entity.Member;
import com.example.music_sharing.entity.dto.MemberDto;
import com.example.music_sharing.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/registerOk")
    public String registerOk(MemberDto memberDto) {
        memberService.createAccount(memberDto);
        return "redirect:/login";
    }
}
