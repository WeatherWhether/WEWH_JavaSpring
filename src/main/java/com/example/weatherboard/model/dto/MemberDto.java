package com.example.weatherboard.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "member")
@Entity(name = "MemberDto")
public class MemberDto {

    @Id
    @NotBlank
    private String memberId;

    @NotBlank
    private String password;

    private String memberArea1; // 일단은 1개만
    private String memberClass; // 냉방인 난방인 반반인
    private String memberRole;  // 권한(admin/member)
}
