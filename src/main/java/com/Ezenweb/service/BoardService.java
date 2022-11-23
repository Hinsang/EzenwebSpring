package com.Ezenweb.service;

import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.bcategory.BcategoryRepository;
import com.Ezenweb.domain.entity.board.BoardEntity;
import com.Ezenweb.domain.entity.board.BoardRepository;
import com.Ezenweb.domain.entity.member.MemberEntity;
import com.Ezenweb.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService {
    // ------------1.전역변수[ 현재 서비스객체 ]---------------//
    @Autowired
    private HttpServletRequest request; // 요청 객체 선언
    @Autowired
    private HttpServletResponse response; // 응답 객체 선언
    @Autowired
    private MemberRepository memberRepository; // 회원 리포지토리 객체 선언
    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardRepository boardRepository;// 게시물 리포지토리 객체 선언
    @Autowired
    private BcategoryRepository bcategoryRepository;
    // 첨부파일 경로
    String path = "C:\\Users\\504\\Desktop\\springweb\\EzenwebSpring\\src\\main\\resources\\static\\bupload\\";
    // @Transactional : 엔티티 DML 적용 할때 사용되는 어노테이션
    // 1. 메소드
            /*
                1. insert : boardRepository.save( 삽입할엔티티 )            BoardEntity entity
                2. select : boardRepository.findAll()                List<BoardEntity> elist
                3. select : boardRepository.findById( pk번호 )        Optional<BoardEntity> optional
                4. delete : boardRepository.delete( 삭제할엔티티 )
             */
    // ------------ 2. 서비스 ------------- //

    // 0. 첨부파일 다운로드
    public void filedownload( String filename ) {
        String realfilename = ""; // uuid 제거
        String [] split = filename.split("_"); // _ 기준으로 자르기
        // (uuid는 _ 포함이 안되므로 uuid 다음의 파일명까지 끊기 위해서 i는 1부터 시작한다.)
        for( int i = 1 ; i < split.length ; i++ ) { // 2. uuid 제외한 반복문 돌리기
            realfilename += split[i];               // 3. 뒷자리 문자열 추가
            if(split.length-1 != i) {   // 마지막 인덱스 아니면
                // 파일명에서 _로 split으로 나누었기에 _는 포함이 안되므로 _문자열을 split배열 중간중간에 추가해준다.
                realfilename += "_";        // 문자열[1] _ 문자열[2] _ 문자열[3].확장자명
            }
        }
        // 1. 경로 찾기
        String filepath = path+filename;
        // 2. 헤더 구성 [ HTTP 해서 지원하는 다운로드형식 메소드 [ response ]
        try {
            response.setHeader(
                    "Content-Disposition", // 다운로드 형식 [ 브라우저 마다 다름 ]
                    "attachment;filename=" + URLEncoder.encode(realfilename, "UTF-8")); // 다운로드에 표시할 파일명
            File file = new File(filepath); // 해당 경로의 파일 객체화
        // 3. 다운로드 스트림 [ ]
            // 1. 다운로드 할 파일 바이트 읽어올 스트림 객체 선언
            BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
            // 2. 파일의 길이만큼 배열 선언
            byte[] bytes = new byte[(int)file.length()];
            // 3. 파일의 길이만큼 읽어와서 바이트를 배열에 저장
            fin.read(bytes); // * 스트림 읽기 [ 대상 : new FileInputStream(file) ]
            // 4. 출력 스트림 객체 선언
            BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());
            // 5. 응답하기 [ 배열 내보내기 ]
            fout.write(bytes); // * 스트림 내보내기 [ response.getOutputStream() ]
            // 6. 버퍼 초기화 혹은 스트림 닫기
            fout.flush(); fout.close(); fin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 1. 게시물 쓰기
    @Transactional
    public boolean setboard( BoardDto boardDto ){
        // ---------- 로그인 회원 찾기 메소드 실행 --> 회원엔티티 검색 --------------  //
        MemberEntity memberEntity = memberService.getEntity();
        if( memberEntity == null ){ return false; }
        // ---------------------------- //
        // ------------ 선택한 카테고리 번호 --> 카테고리 엔티티 검색 --------------  //
        Optional<BcategoryEntity> optional = bcategoryRepository.findById( boardDto.getBcno() );
        if ( !optional.isPresent() ) { return false;}
        BcategoryEntity bcategoryEntity = optional.get();
        // --------------------------  //
        BoardEntity boardEntity  = boardRepository.save( boardDto.toEntity() );  // 1. dto --> entity [ INSERT ] 저장된 entity 반환
        if( boardEntity.getBno() != 0 ){   // 2. 생성된 entity의 게시물번호가 0 이 아니면  성공

            // 1. MultipartFile 인터페이스
                // .getOriginalFilename() : 해당 인터페이스에 연결(주소)된 파일의 이름 호출
                // .transferTo() : 파일이동[ 사용자 pc ---> 개발자 pc ]
                    // .transferTo(파일객체)
                    // File : java 외 파일을 객체화 클래스
                        // new File("경로") : 해당 경로의 파일을 객체화

            if(boardDto.getBfile() != null){
                // * 업로드 된 파일의 이름 [ 문제점 : 파일명 중복 ]
                String uuid = UUID.randomUUID().toString(); // 1. 난수생성
                String filename = uuid+"_"+boardDto.getBfile().getOriginalFilename(); // 2. 난수+파일명

                // * 첨부파일명 db에 등록
                boardEntity.setBfile( filename ); // 해당 파일명 엔티티에 저장 // 3. 난수+파일명 엔티티에 저장

                // * 첨부파일 업로드

                try {
                    File uploadfile = new File(path + filename);
                    boardDto.getBfile().transferTo(new File(path + filename));
                    System.out.println("첨부파일 업로드 성공!!");
                } catch(Exception e) {
                    System.out.println("첨부파일 업로드 실패!!");
                }
            }

            // 1. 회원 <---> 게시물 연관관계 대입
            boardEntity.setMemberEntity( memberEntity ); // ***!!!! 5. fk 대입
            memberEntity.getBoardEntityList().add( boardEntity ); // *** 양방향 [ pk필드에 fk 연결 ]
            // 2. 카테고리 <---> 게시물 연관관계 대입
            boardEntity.setBcategoryEntity( bcategoryEntity );
            bcategoryEntity.getBoardEntityList().add( boardEntity );

            return true;
        }
        else{ return false; } // 2. 0 이면 entity 생성 실패
    }
    // 2. 게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist(int bcno){
        List<BoardEntity> elist =  null; // if 밖에서도 쓰기 위해
        if( bcno == 0 ) {
            elist = boardRepository.findAll(); // 카테고리번호가 0 이면 전체보기
        } else {    // 카테고리번호가 0이 아니면 선택된 카테고리별 보기
            BcategoryEntity bcEntity = bcategoryRepository.findById(bcno).get();
            elist = bcEntity.getBoardEntityList();
        }
        List<BoardDto> dlist = new ArrayList<>(); // 2. 컨트롤에게 전달할때 형변환[ entity->dto ] : 역할이 달라서
        for( BoardEntity entity : elist ){ // 3. 변환
            dlist.add( entity.toDto() );
        }
        return dlist;  // 4. 변환된 리스트 dlist 반환
    }
    // 3. 게시물 개별 조회
    @Transactional
    public BoardDto getboard( int bno ){
        Optional< BoardEntity > optional = boardRepository.findById(bno); // 1. 입력받은 게시물번호로 엔티티 검색 [ Optional]
        if( optional.isPresent() ){// 2. Optional 안에 있는 내용물 확인 .isPresent()
            BoardEntity boardEntity = optional.get(); // 3. 엔티티 꺼내기 .get();
            return boardEntity.toDto(); // 4.형변환 반환
        }else{
            return null; // 4. 없으면 null
        }
    }
    // 4. 게시물 삭제
    @Transactional
    public boolean delboard( int bno ){
        Optional<BoardEntity> optional = boardRepository.findById( bno);
        if( optional.isPresent() ){
            BoardEntity entity =  optional.get();
            boardRepository.delete( entity ); // 찾은 엔티티를 삭제한다.
            return true;
        }else{ return false; }
    }
    // 5. 게시물 수정 [ 첨부파일 ]
    @Transactional
    public boolean upboard( BoardDto boardDto){
        // 1. DTO에서 수정할 PK번호 이용해서 엔티티 찾기
        Optional<BoardEntity> optional = boardRepository.findById( boardDto.getBno() );
        if( optional.isPresent() ){  // 2.
            BoardEntity entity = optional.get();
            // * 수정처리 [ 메소드 별도 존재x /  엔티티 객체 <--매핑--> 레코드 / 엔티티 객체 필드를 수정 : @Transactional ]
            entity.setBtitle( boardDto.getBtitle() );
            entity.setBcontent( boardDto.getBcontent()) ;
            return true;
        }else{  return false;  }
    }
    // 6. 카테고리 등록
    public boolean setbcategory( BcategoryDto bcategoryDto ){
        BcategoryEntity entity =  bcategoryRepository.save( bcategoryDto.toEntity() );
        if( entity.getBcno() != 0 ){ return  true;}
        else{ return false; }
    }
    // 7. 모든 카테고리 출력
    public List<BcategoryDto> bcategorylist(){
        // DB에서 일치하는 엔티티 리스트 가져와서 저장
        List<BcategoryEntity> entityList =  bcategoryRepository.findAll();
        // dto리스트 객체 생성
        List<BcategoryDto> dtolist = new ArrayList<>();
        // 엔티티리스트를 반복문을 돌려서 dtolist에 엔티티안에 dto 변환 메소드를 추가함
        entityList.forEach( e -> dtolist.add( e.toDto() ) );
        // dto 리스트 반환
        return dtolist;
    }
}
/*
        // 1. 리스트를 순회하는 방법 3가지 [
        // 화살표함수[람다식표현] js : ( 인수 )  => { 실행코드 }    java : 인수 -> { 실행코드 }
        for( int i = 0 ; i < entityList.size(); i++ ){
            BcategoryEntity e =  entityList.get(i);
            System.out.println( e.toString() );
        }
        for ( BcategoryEntity e : entityList ){
            System.out.println( e.toString() );
        }
        entityList.forEach( e -> e.toString()  );
 */

// 1. pk + 파일명
// 2. uuid + 파일명 [ UUID (범용 고유 식별자 클래스 : UUID.randomUUID().toString() ]
// 3. 업로드 날짜/시간 + 파일명
// 4. 중복된파일명 중 최근 파일명 뒤에 파일명 + (중복수+1)
