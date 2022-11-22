package com.Ezenweb.service;

import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.domain.dto.BtDto;
import com.Ezenweb.domain.dto.BtcategoryDto;
import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.board.BoardEntity;
import com.Ezenweb.domain.entity.boardtest.BtEntity;
import com.Ezenweb.domain.entity.boardtest.BtRepository;
import com.Ezenweb.domain.entity.boardtest.BtcategoryEntity;
import com.Ezenweb.domain.entity.boardtest.BtcategoryRepository;
import com.Ezenweb.domain.entity.member.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardTestService {
    @Autowired
    private BtcategoryRepository btcategoryRepository;

    @Autowired
    private BtRepository btRepository;

    // 카테고리 등록
    public boolean setcategory(BtcategoryDto btcategoryDto) {
        // entity객체에 레파지토리.save를 통해서 dto안의 toEntity로 형변환을 해서 저장한다.
        BtcategoryEntity entity = btcategoryRepository.save(btcategoryDto.toEntity());
        // entity에 get카테고리번호가 있으면 등록성공이므로 true를 리턴한다.
        if(entity.getBtcno() != 0) {
            return true;
        } else {
            return false;
        }
    }

    // 카테고리 출력
    // 전체카테고리 리스트 자료형을 dto로 반환할 것이다.
    public List<BtcategoryDto> categorylist() {
        // 레파지토리.findAll을 사용하여 모두 찾아서 엔티티 자료형의 변수로 받는다.
        List<BtcategoryEntity> entityList = btcategoryRepository.findAll();
        // 엔티티 객체를 담을 ArrayList 객체를 호출한다.
        List<BtcategoryDto> dtoList = new ArrayList<>();
        // 받은 엔티티객체를 forEach문을 돌려서 호출한 dtoList에 엔티티리스트를 dto로 형변환 한 것을 추가한다.
        entityList.forEach(e -> dtoList.add(e.toDto()));
        // dtoList를 리턴한다.
        return dtoList;
    }

    // 비회원제 게시글 등록
    @Transactional
    public boolean setboard(BtDto btDto) {
        // Optional 클래스는 NPE(널포인터익셉션) 에러를 방지해준다.
        // value에 배열에 값을 저장해주기 때문이다. 또한, 다양한 메소드도 제공해준다.
        // 보통 레파지토리.findById의 값을 저장할때 주로 쓰인다.
        // 아래는 레파지토리.findById를 활용해 btDto안의 getBtcno(카테고리번호)를 가져와 옵셔널 클래스의 옵셔널 변수에 저장하였다.
        Optional<BtcategoryEntity> optional = btcategoryRepository.findById( btDto.getBtcno() ); // 카테고리에서 일치하는 번호 찾기
        // 게시글레파지토리.save를 이용해 btDto를 toEntity로 형변환 한것을 BtEntity클래스의 btEntity 변수에 저장하였다.
        BtEntity btEntity = btRepository.save(btDto.toEntity()); // 게시글레파지토리에 엔티티로 형변환해서 저장
        // 카테고리가 일치하는 값이 없으면 false반환
        if(!optional.isPresent()) {
            return false;
        }
        // 카테고리가 일치하는 값이 있으면 옵셔널객체안에 있는 카테고리 번호가 일치하는 dto를 카테고리 엔티티클래스 변수에 저장한다.
        BtcategoryEntity btcategoryEntity = optional.get(); // 카테고리가 일치하는 객체를 엔티티에 저장
        if(btEntity.getBtno() != 0) { // 엔티티에 저장이 됐으면 ( int의 기본값이 0이므로 )
            // 최종 결과값을 각자 관계의 메소드에 저장한다.
            btEntity.setBtcategoryEntity(btcategoryEntity); // 게시글안에 카테고리 엔티티추가 (fk)
            btcategoryEntity.getBtEntityList().add(btEntity); // 카테고리안에 게시글 엔티티추가 (pk)
            return true; // 성공값 반환
        } else { // 엔티티에 저장이 안됐으면
            return false; // 실패값 반환
        }
    }

    // 카테고리별 글 조회
    @Transactional
    public List<BtDto> boardlist(int btcno) {
        List<BtEntity> elist = null;
        if(btcno == 0) {
            elist = btRepository.findAll();
        } else {
            BtcategoryEntity btcategoryEntity = btcategoryRepository.findById(btcno).get();
            elist = btcategoryEntity.getBtEntityList();
        }
        List<BtDto> dlist = new ArrayList<>();
        // 엔티티 리스트를 dto리스트에 저장해서 리턴해준다.
        for(BtEntity btEntity : elist ) {
            dlist.add(btEntity.toDto());
        }
        return dlist;
    }

    // 수정
    @Transactional
    public boolean setupdate(BtDto btDto) {
        Optional<BtEntity> optional = btRepository.findById(btDto.getBtno());
        if(optional.isPresent()) {
            BtEntity btEntity = optional.get();
            btEntity.setBtname(btDto.getBtname());
            btEntity.setBtcontent(btDto.getBtcontent());
            return true;
        } else {
            return false;
        }
    }

    // 삭제
    @Transactional
    public boolean setdelete(int btno) {
        Optional<BtEntity> optional = btRepository.findById(btno);
        if(optional.isPresent()) {
            BtEntity btEntity = optional.get();
            btRepository.delete(btEntity);
            return true;
        } else {
            return false;
        }
    }

}
