package com.example.weatherboard.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.weatherboard.model.dto.AreaDto;

public interface AreaRepository extends JpaRepository<AreaDto, Long> {

    @Query(value="SELECT a.* FROM area a WHERE 1=1 AND a.name_ko = :memberArea1", nativeQuery=true)
    public AreaDto getAreaByName(@Param("memberArea1") String memberArea1);

    @Query(value="SELECT a.code FROM area a WHERE 1=1 AND a.name_ko = :memberArea1", nativeQuery=true)
    public String getAreaCodeByName(@Param("memberArea1") String memberArea1);
}