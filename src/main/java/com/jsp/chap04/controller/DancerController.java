package com.jsp.chap04.controller;


import com.jsp.chap04.service.DeleteService;
import com.jsp.chap04.service.IDancerService;
import com.jsp.chap04.service.RegistService;
import com.jsp.chap04.service.showList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//서블릿 -> 컨드롤러
// 클라이언트의 요청을 파악하고 모델 에게 로직을 위임하여
// 응답할 view를 결정함
@WebServlet("*.do") // 확장자 패턴 -> 앞이 뭐든 간에 .do 로 끝나는 요청이라면 이 서블릿이 다 받음
public class DancerController extends HttpServlet {
    
    // 인터페이스 타입의 변수를 선언해서 상황에 맞게 서비스 객체를 갈아 끼울 예정
    private IDancerService sv;
    private RequestDispatcher rd;





    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        try {
            switch (requestURI){

                case "/register.do":
                    System.out.println("등록 홈으로 이동시켜 달라는 요청");
                    rd = request.getRequestDispatcher("/WEB-INF/chap03/dancer/register.jsp");
                    rd.forward(request, response);
                    break;
                case  "/regist.do" :
                    System.out.println("댄서 등록 요청");
                    sv = new RegistService();
                    sv.exeute(request, response);

                    // 서비스가 댄서 목록을 request에 담아 놓은 상태
                    // 디스패처에게 목적지를 알려주면서 request와 response 객체를 가지고 이동하라는 명령을 내림
                    // 실제 페이지가 목적지로 이동되면서, reponse와 response 객체도 함께 전달 됨
                    // -> jsp 에서 request 를 꺼내서 목록을 화면에 뿌려서 응답하면 됨
                    rd = request.getRequestDispatcher("/WEB-INF/chap03/dancer/list.jsp");
                    rd.forward(request, response);
                    break;

                case  "/delete.do" :
                    System.out.println("삭제 요청이 들어옴");
                    sv = new DeleteService();
                    sv.exeute(request,response);

                    rd = request.getRequestDispatcher("/WEB-INF/chap03/dancer/list.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "/list.do" :
                    System.out.println("목록 화면 요청이 들어옴");
                   sv = new showList();
                   sv.exeute(request, response);

                    rd = request.getRequestDispatcher("/WEB-INF/chap03/dancer/list.jsp");
                    rd.forward(request, response);
                    break;
    //            default:
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
