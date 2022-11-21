package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.boardtest.BtEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BtDto {
    private int btno;
    private String btname;
    private String btcontent;
    private int btcno;

    public BtEntity toEntity() {
        return BtEntity.builder()
                .btno(this.btno)
                .btname(this.btname)
                .btcontent(this.btcontent)
                .build();
    }
}
