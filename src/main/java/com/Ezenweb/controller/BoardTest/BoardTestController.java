package com.Ezenweb.controller.BoardTest;

import com.Ezenweb.domain.dto.BtDto;
import com.Ezenweb.domain.dto.BtcategoryDto;
import com.Ezenweb.service.BoardTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boardtest")
public class BoardTestController {

    @Autowired
    private BoardTestService boardTestService;

    @PostMapping("/setcategory")
    public boolean setcategory(@RequestBody BtcategoryDto btcategoryDto) {
        return boardTestService.setcategory(btcategoryDto);
    }

    @GetMapping("/categorylist")
    public List<BtcategoryDto> categorylist() {
        return boardTestService.categorylist();
    }

    @PostMapping("/setboard")
    public boolean setboard(@RequestBody BtDto btDto) {
        System.out.println(btDto);
        return boardTestService.setboard(btDto);
    }

}
