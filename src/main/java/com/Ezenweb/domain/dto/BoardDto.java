package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.board.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

// 롬복 : 생성자, GET/SET, ToString, 빌더패턴
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardDto {
    private int bno;            // 글번호
    private String btitle;      // 글제목
    private String bcontent;    // 글내용
    private int bview;          // 조회수
    private MultipartFile bfile;// 첨부파일 객체 [ 업로드용 ]
    private String bfilename;   // 첨부파일 [ 출력용 ]
//  private int mno;            // 작성자 [ 회원번호-fk ]
    private int bcno;           // 카테고리 [ 카테고리-fk ]
    private String memail;      // 회원아이디

    public BoardDto(int anInt, String string, String string1) {
    }

    // 1. 형변환
    public BoardEntity toEntity() {
        return BoardEntity
                .builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .build();
    }
}
