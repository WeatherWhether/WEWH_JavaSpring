package com.example.weatherboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.weatherboard.model.dto.MemberDto;
import com.example.weatherboard.model.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    // join
    public void insertMem(MemberDto dto){

        dto.setMemberRole("USER");
        if(dto.getMemberId().equals("admin")){
            dto.setMemberRole("ADMIN");
        }

        String rawPw = dto.getPassword();
        String encPw = bCryptPasswordEncoder.encode(rawPw);
        dto.setPassword(encPw);

        memberRepository.save(dto);
    }

    // select
    public List<MemberDto> selectAllMem() {
        List<MemberDto> dtoList = memberRepository.findAll();
        log.info("[MemberService][selectAllMem] 실행");
        return dtoList;
    }

    // select by id(String)
    public MemberDto findMem(String id) {
        MemberDto dto = memberRepository.findById(id).get();
        return dto;
    }

    // update
    public void updateMem(MemberDto dto) {
        memberRepository.save(dto);
        log.info("[MemberService][updateMem] 실행");
    }

    // delete
    public void deleteMem(String memberID) {
        memberRepository.deleteById(memberID);
        log.info("[MemberService][deleteMem] 실행");
    }

}
