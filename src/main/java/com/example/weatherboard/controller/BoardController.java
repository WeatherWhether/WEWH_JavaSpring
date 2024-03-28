package com.example.weatherboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.weatherboard.model.dto.BoardDto;
import com.example.weatherboard.service.BoardService;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    // 메인보드 화면
    @GetMapping("/mainboard")
    public String mainboard(Model model, Authentication authentication) {
        
        // session check
        if(authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("memberId", userDetails.getUsername());
        }

        model.addAttribute("mainboard", boardService.getBoardDesc());
        return "/main/index";
    }
    
    // 메인보드에서 멤버용 댓글달기 기능
    @PostMapping("/member/reply/index")
    public String replyIndex(@ModelAttribute BoardDto boardDto, Authentication authentication) {
        String memberId = authentication.getName();
        boardDto.setMemberId(memberId);
        boardService.saveBoard(boardDto);
        return "redirect:/mainboard";
    }

    // 멤버화면에서 멤버용 댓글달기 기능
    @PostMapping("/member/reply/custom")
    public String replyMember(@ModelAttribute BoardDto boardDto, Authentication authentication) {
        String memberId = authentication.getName();
        boardDto.setMemberId(memberId);
        boardService.saveBoard(boardDto);
        return "redirect:/member/custom";
    }

    //  //비회원용 댓글달기 기능
    // @PostMapping("/all/reply")
    // public String reply(@ModelAttribute String comment) {
    //     BoardDto boardDto = new BoardDto();

    //     String memberId = "somebody";
    //     String memberArea1 = "somewhere";
    //     String memberClass = "something";
        
    //     boardDto.setMemberId(memberId);
    //     boardDto.setMemberArea1(memberArea1);
    //     boardDto.setMemberClass(memberClass);
    //     boardDto.setComment(comment);

    //     return "redirect:/allboard";
    // }
}
