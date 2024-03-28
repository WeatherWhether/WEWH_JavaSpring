package com.example.weatherboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.weatherboard.model.dto.AreaDto;
import com.example.weatherboard.model.repository.AreaRepository;

@Service
public class AreaService {
    @Autowired
    private AreaRepository areaRepository;

    public List<AreaDto> allArea() {
        List<AreaDto> areaList = areaRepository.findAll();
        return areaList;
    }

    // 지역명(경기도 성남시) 통해 AreaDto 불러오기
    public AreaDto getAreaByName(String memberArea1) {
        return areaRepository.getAreaByName(memberArea1);
    }

    // 지역명 통해 AreaCode 불러오기
    public String getAreaCodeByName(String memberArea1) {
        return areaRepository.getAreaCodeByName(memberArea1);
    }
}
