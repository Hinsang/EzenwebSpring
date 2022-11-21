package com.Ezenweb.domain.entity.boardtest;

import com.Ezenweb.domain.dto.BtDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "boardtest")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int btno;
    private String btname;
    private String btcontent;

    @ManyToOne
    @JoinColumn(name="btcno")
    @ToString.Exclude
    private BtcategoryEntity btcategoryEntity;

    public BtDto toDto() {
        return BtDto.builder()
                .btno(this.btno)
                .btname(this.btname)
                .btcontent(this.btcontent)
                .build();
    }
}
