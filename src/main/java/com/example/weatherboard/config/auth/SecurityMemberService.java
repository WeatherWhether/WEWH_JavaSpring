package com.example.weatherboard.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.weatherboard.model.dto.MemberDto;
import com.example.weatherboard.model.repository.MemberRepository;

@Service
public class SecurityMemberService implements UserDetailsService{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        MemberDto memberDto = memberRepository.getMemberDtoByMemberId(memberId);
        
        if(memberDto != null){
            return new SecurityMemberDto(memberDto);
        }
        throw new UsernameNotFoundException(memberId); // 로그인 무반응 해결
    }

    

}
