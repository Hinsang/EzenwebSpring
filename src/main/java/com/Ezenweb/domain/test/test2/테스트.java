package com.Ezenweb.domain.test.test2;

public class 테스트 {
    public static void main(String[] args) {

        제품 제품1 = new 제품();
        제품1.제품명 = "나이키신발";

        이미지 이미지1 = new 이미지();
        이미지1.이미지명 = "나이키신발1.jpg";

        이미지 이미지2 = new 이미지();
        이미지2.이미지명 = "나이키신발2.jpg";

        // 이미지 객체[FK] ---> 제품 객체 [ PK ] 대입
        이미지1.제품 = 제품1;
        이미지2.제품 = 제품1;

        // 제품객체[pk] ---> 이미지객체[fk] 넣자.
        제품1.이미지List.add(이미지1);
        제품1.이미지List.add(이미지2);

        // 확인
        System.out.println(제품1.이미지List);
    }
}
