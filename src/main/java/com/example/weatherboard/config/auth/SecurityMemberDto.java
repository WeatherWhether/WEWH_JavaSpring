package com.example.weatherboard.config.auth;

import java.util.Collection;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.weatherboard.model.dto.MemberDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityMemberDto implements UserDetails{
    
    private MemberDto memberDto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority() {
            
            @Override
            public String getAuthority(){
                return memberDto.getMemberRole();
            }
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return memberDto.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return memberDto.getMemberId();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }


}
