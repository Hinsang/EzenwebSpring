package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.boardtest.BtcategoryEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BtcategoryDto {
    int btcno;
    String btcname;

    public BtcategoryEntity toEntity() {
        return BtcategoryEntity.builder()
                .btcno(this.btcno)
                .btcname(this.btcname)
                .build();
    }
}
