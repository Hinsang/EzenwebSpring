package com.Ezenweb.domain.dto;

import lombok.*;

@NoArgsConstructor // 빈생성자
@AllArgsConstructor // 풀생성자
@Getter
@Setter
@ToString
@Builder
public class MemberDto {

    private String name;
    private String email;
    private String organization;

}
