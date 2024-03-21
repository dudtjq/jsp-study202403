package com.jsp.chap04.service;

// 등록, 목록, 삭제 서비스 객체의 메서드 이름을 통일 시키고,
// 객체 생성 시 다형성을 활용하여 하나의 타입으로 규격화 하여
// 상황에 맞게 객체를 갈이끼을 용도로 활용할 인터페이스

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IDancerService {
    
    // 모든 서비스 객체들은 excute() 매서드 이름으로 움직일 것이고
    // 컨트롤러로 부터 request 와 response 객체를 전달 받아 잡무를 처리
    void exeute(HttpServletRequest request, HttpServletResponse response)throws Exception;
    
}
