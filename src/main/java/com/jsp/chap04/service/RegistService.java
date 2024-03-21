package com.jsp.chap04.service;

import com.jsp.entity.Dancer;
import com.jsp.repository.DancerRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.jsp.repository.DancerRepository.save;

public class RegistService implements IDancerService{


    @Override
    public void exeute(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("utf-8");

        // 제공된 form에 작성된 사용자의 입력값 가져오기
        String name = request.getParameter("name");
        String crewName = request.getParameter("crewName");
        String danceLevel = request.getParameter("danceLevel");
        // 체크박스 같이 여러 값을 전달 받는 경우에는 getParameterValues 를 사용한다 -> String 배열로 리턴됨
        String[] genresArray = request.getParameterValues("genres");

        DancerRepository.save(name, crewName, danceLevel, genresArray);

        ////////////////////////////////////////// 등록은 여기까지 ///////////////////////////////////////////////

        // 등록이 완료 되었으므로 지금까지 등록된 댄서의 목록을 화면에 보여주고 싶음
        // MVC 구조에서 화면 응답은 view 가 전달 하기 때문에, 서비스는 repository 로 부터 목록을 받아와서
        // view 단으로 데이터를 넘기 겠다.(request에 담아서 전달)

        // 목록 받아오기
        List<Dancer> dancerList = DancerRepository.findAll();

        // request에 담기
        // setAttribute("이름", 저장 할 값) -> 이름을 통해 값을 얻어 올 수 있음
        request.setAttribute("dl", dancerList);
        
        // 서비스의 역할은 여기 까지
        // 화면 단 결정은 Controller의 몫임


        
        
        
    }
}
