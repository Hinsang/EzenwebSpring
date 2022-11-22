package com.Ezenweb.domain.entity.boardtest;

import com.Ezenweb.domain.dto.BtcategoryDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="btcategory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BtcategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int btcno;
    String btcname;

    // 서로 엔티티 간의 관계를 나타내는 메소드
    @OneToMany(mappedBy = "btcategoryEntity")
    //@Builder.Default // 빌더 사용시 메모리 할당
    @ToString.Exclude
    private List<BtEntity> btEntityList = new ArrayList<>();

    public BtcategoryDto toDto() {
        return BtcategoryDto.builder()
                .btcno(this.btcno)
                .btcname(this.btcname)
                .build();
    }
}
