package com.example.weatherboard.model.dto;


import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "board")
@Entity(name = "BoardDto")
public class BoardDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String memberId;
    private String memberArea1; // 일단은 1개만
    private String memberClass; // 냉방인 난방인 반반인
    private String comment;

    @CreationTimestamp
    private Timestamp createdate;  // 댓글입력시간 자동생성
}
