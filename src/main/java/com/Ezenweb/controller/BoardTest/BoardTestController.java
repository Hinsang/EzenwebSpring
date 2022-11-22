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

    @GetMapping("/boardlist")
    public List<BtDto> boardlist(@RequestParam int btcno) {
        return boardTestService.boardlist(btcno);
    }

    @PutMapping("/setupdate")
    public boolean setupdate(@RequestBody BtDto btDto) {
        return boardTestService.setupdate(btDto);
    }

    @DeleteMapping("/setdelete")
    public boolean setdelete(@RequestParam("btno") int btno) {
        return boardTestService.setdelete(btno);
    }

}
