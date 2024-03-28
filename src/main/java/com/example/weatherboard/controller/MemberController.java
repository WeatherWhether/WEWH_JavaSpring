package com.example.weatherboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.weatherboard.model.dto.AreaDto;
import com.example.weatherboard.model.dto.MemberDto;
import com.example.weatherboard.service.AreaService;
import com.example.weatherboard.service.BoardService;
import com.example.weatherboard.service.MemberService;

import jakarta.validation.Valid;

@Controller
public class MemberController {
    @Autowired
    private AreaService areaService;
    @Autowired
    private BoardService boardService;    
    @Autowired
    private MemberService memberService;

    // 가입 양식
    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("areaList", areaService.allArea());
        return "/details/join";
    }

    // 가입 요청 -> 로그인 양식
    @PostMapping("/join")
    public String join(@Valid @ModelAttribute MemberDto dto) {
        memberService.insertMem(dto);
        return "redirect:/loginForm";
    }

    // 로그인 양식
    @GetMapping("/loginForm")
    public String loginForm(@RequestParam(value = "errorMessage", required = false) String errorMsg, Model model) {
        model.addAttribute("errorMessage", errorMsg);
        return "/details/login";
    }

    // 멤버 커스텀 창
    @GetMapping("/member/custom")
    public String member(Model model, Authentication authentication) {
        String memberId = authentication.getName();
        MemberDto memberDto = memberService.findMem(memberId);
        AreaDto areaDto = areaService.getAreaByName(memberDto.getMemberArea1());
        model.addAttribute("memberDto", memberDto);
        model.addAttribute("areaDto", areaDto);
        model.addAttribute("mainboard", boardService.getBoardDesc());
        return "/main/member";
    }

    // 관리자 창
    @GetMapping("/admin")
    public String admin() {
        return "/main/admin";
    }
}