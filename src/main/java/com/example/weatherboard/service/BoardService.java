package com.example.weatherboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.weatherboard.model.dto.BoardDto;
import com.example.weatherboard.model.dto.MemberDto;
import com.example.weatherboard.model.repository.BoardRepository;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberService memberService;

    // select
    public List<BoardDto> allBoard() {
        List<BoardDto> boardList = boardRepository.findAll();
        return boardList;
    }

    // getBoardDesc (최신순으로 보여주기)
    public List<BoardDto> getBoardDesc() {
        List<BoardDto> boardList = boardRepository.getBoardDesc();
        return boardList;
    }

    // insert
    public void saveBoard(BoardDto dto) {
        MemberDto memberDto = memberService.findMem(dto.getMemberId());
        dto.setMemberId(memberDto.getMemberId());
        dto.setMemberArea1(memberDto.getMemberArea1());
        dto.setMemberClass(memberDto.getMemberClass());
        dto.setComment(dto.getComment());
        boardRepository.save(dto);
    }

    // api insert
    public void saveComment(String memberId, String comment) {
        MemberDto memberDto = memberService.findMem(memberId);
        BoardDto boardDto = new BoardDto();
        boardDto.setMemberId(memberDto.getMemberId());
        boardDto.setMemberArea1(memberDto.getMemberArea1());
        boardDto.setMemberClass(memberDto.getMemberClass());
        boardDto.setComment(comment);
        boardRepository.save(boardDto);
    }

    // update
    public void updateBoard(BoardDto dto) {
        BoardDto updateDto = boardRepository.getReferenceById(dto.getId());
        updateDto.setComment(dto.getComment());
        boardRepository.save(updateDto);
    }

    // delete
    public void delete(Long number) {
        boardRepository.deleteById(number);
    }
}
