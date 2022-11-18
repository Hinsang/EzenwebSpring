package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.member.MemberEntity;
import lombok.*;

@NoArgsConstructor // 빈생성자
@AllArgsConstructor // 풀생성자
@Getter @Setter
@ToString @Builder
public class MemberDto {

    private int mno;
    private String memail;
    private String mpassword;
    private String mphone;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mphone(this.mphone)
                .build();
    }

}
