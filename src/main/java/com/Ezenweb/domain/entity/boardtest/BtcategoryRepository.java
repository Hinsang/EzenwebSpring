package com.Ezenweb.domain.entity.boardtest;

import com.Ezenweb.domain.dto.BtcategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BtcategoryRepository extends JpaRepository<BtcategoryEntity,Integer> {

}
