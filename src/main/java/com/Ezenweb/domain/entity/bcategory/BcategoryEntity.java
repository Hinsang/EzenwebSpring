package com.Ezenweb.domain.entity.bcategory;

import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.board.BoardEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bcategory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BcategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bcno;       // 카테고리번호
    private String bcname;  // 카테고리이름

    @OneToMany(mappedBy = "bcategoryEntity") // 양방향 참조를 위해 써주었다.(엔티티 객체와 맞춰야한다.)
    // 말그대로 bcategoryEntity에 의해 매핑된다는 의미이다.
    @ToString.Exclude
    private List<BoardEntity> boardEntityList
            = new ArrayList<>();

}
