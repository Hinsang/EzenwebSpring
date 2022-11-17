package com.Ezenweb.domain.entity;

import com.Ezenweb.domain.dto.BoardDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity // 엔티티 정의
@Table(name = "board") // 테이블명 정의
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardEntity {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int bno;            // 글번호
    @Column(nullable = false)   // not null
    private String btitle;      // 글제목
    @Column(nullable = false, columnDefinition = "TEXT")   // not null, DB 자료형 사용시 columnDefinition = "DB자료형"
    private String bcontent;    // 글내용
    @Column(nullable = false)   // not null
    @ColumnDefault("0")         // JPA insert 할 경우 default
    private int bview;          // 조회수
    @Column(nullable = false)   // not null
    private String bfile;       // 첨부파일
    @Column(nullable = false)   // not null
    private int mno;            // 작성자 [ 회원번호-fk ]
    @Column(nullable = false)   // not null
    private int cno;            // 카테고리 [ 카테고리-fk ]
    // 작성일, 수정일 -> 상속( 여러 엔티티에서 사용되는 필드라서 )

    // 1.형변환
    public BoardDto toDto() {
        // * 빌더 패턴을 이용한 객체생성
        return BoardDto.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .bfile(this.bfile)
                .mno(this.mno)
                .cno(this.cno)
                .build();
    }
}

/*

    자바 ----------------------> DB자료형
    int                         int
    double float                float
    String                      varchar
      X
            columnDefinition = "DB자료형"

 */
