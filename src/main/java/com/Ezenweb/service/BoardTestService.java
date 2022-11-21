package com.Ezenweb.service;

import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.domain.dto.BtDto;
import com.Ezenweb.domain.dto.BtcategoryDto;
import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
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
        BtcategoryEntity entity = btcategoryRepository.save(btcategoryDto.toEntity());
        if(entity.getBtcno() != 0) {

            return true;
        } else {
            return false;
        }
    }

    // 카테고리 출력
    public List<BtcategoryDto> categorylist() {
        List<BtcategoryEntity> entityList = btcategoryRepository.findAll();
        List<BtcategoryDto> dtoList = new ArrayList<>();
        entityList.forEach(e -> dtoList.add(e.toDto()));
        return dtoList;
    }

    // 비회원제 게시글 등록
    @Transactional
    public boolean setboard(BtDto btDto) {
        Optional<BtcategoryEntity> optional = btcategoryRepository.findById( btDto.getBtcno() );
        BtEntity btEntity = btRepository.save(btDto.toEntity());
        if(!optional.isPresent()) {
            return false;
        }
        BtcategoryEntity btcategoryEntity = optional.get();
        if(btEntity.getBtno() != 0) {
            btEntity.setBtcategoryEntity(btcategoryEntity);
            btcategoryEntity.getBtEntityList().add(btEntity);
            return true;
        } else {
            return false;
        }
    }

}
