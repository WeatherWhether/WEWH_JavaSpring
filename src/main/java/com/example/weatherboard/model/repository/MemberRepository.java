package com.example.weatherboard.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.weatherboard.model.dto.MemberDto;

public interface MemberRepository extends JpaRepository<MemberDto, String>{
    
    @Query(value = "select * from member where member_id = :memberId", nativeQuery = true)
    public MemberDto getMemberDtoByMemberId(@Param(value = "memberId") String memberId);
}
