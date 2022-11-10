package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController //      @ResponseBody 생략 가능 -> ResponseBody
@RequestMapping("/api/v1/put-api")
public class PutController {
    // 1. p.70
    @PutMapping("/member")
    public String putMember(@RequestBody Map<String, String> putData) {
        return putData.toString();
    }

    // 2-1. p.71    반환타입 : 문자열 [ String ]
    @PutMapping("/member1")
    public String postMemberDto(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }

    // 2-2. p.72    반환타입 : DTO [ MemberDto ]
    @PutMapping("/member2")
    @ResponseBody // json 형식으로 형변환해서 리턴(@RestController 쓰면 생략 가능)
    public MemberDto postMemberDto2(@RequestBody MemberDto memberDto) {
        return memberDto;
    }

}
