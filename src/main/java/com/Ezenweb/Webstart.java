package com.Ezenweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication // 스프링 웹 기본설정 [ 1. MVC , 2. 내장서버 , 3. RESTFUL 4. 웹 설정 ]
@EnableJpaAuditing // JPA 감시 [ 생성, 변경 ] p.242
public class Webstart {
    public static void main(String[] args) {
                    // main 스레드
        SpringApplication.run( Webstart.class );
            // 스프링 어플리케이션 실행[ 현재클래스명.class ]
    }
}

/*

    1. extend : 상속 [ 설계도 물려받는다. 1개만 가능 ]
    2. @ 어노테이션 [ 빌드[실행] 할때 자동 코드 실행 / 여러개 가능 ]
        1. 내장 : @override : 상속 메소드 재정의할때
        2. 메타 :

            1. @SpringBootAplication : 컴포넌트 스캔
                컴포넌ㄴ트 스캔 빈[스프링 메모리] 등록
                    1. @Controller
                    2. @RestController

*/
