package com.example.weatherboard.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.weatherboard.model.dto.BoardDto;

public interface BoardRepository extends JpaRepository<BoardDto, Long> {

    @Query(value="SELECT b.* FROM board b WHERE 1=1 ORDER BY createdate DESC", nativeQuery=true)
    public List<BoardDto> getBoardDesc();
}