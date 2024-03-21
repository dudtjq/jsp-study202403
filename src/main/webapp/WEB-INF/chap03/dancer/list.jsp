<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <%
        // 디스패처에 의해 request 객체가 이쪽으로 전달 됬을 것임
        // request에 담겨있는 댄서 리스트를 꺼내는 메서드
     //  List<Dancer> dancerList = (List<Dancer>)request.getAttribute("dl");

        // MVC 구조에서는 View 역할을 하는 jsp쪽에서 자바코드를 최소화 하기로 약속함
        // el을 통해서 request에 있는 dl이라는 이름의 데이터를 꺼내 쓰기


    %>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>list</title>
     <style>
            .del-btn {
                padding: 5px 10px;
                outline: none;
                border: none;
                background: red;
                border-radius: 10px;
                color: #fff;
                margin-left: 10px;
                margin-bottom: 10px;
                cursor: pointer;
            }
            .del-btn:hover {
                border: 1px solid orange;
                opacity: 0.8;
            }
        </style>

</head>
<body>

    <h1>MVC 버전 댄서 목록 뷰</h1>

    <ul id="dancer-list">
        <%--
            c:forEach 태그를 이용하여 향상 for문을 구현하는 법
            제어 변수를 선언하고 items 속성에 반복문을 돌리고자 하는 컬렉션 자료형을 셋팅
           request 객체 내에 있는 dl이라는 이름의 데이터를 el을 사용하여 꺼낼 수 있다
           ${requestScope.dl}-> 이게 원형임 , Scope를 언급하지 않으면 dl 이라는 이름의 데이터를
           전달된 모든 객체에서 뒤져서 꺼내 옴
        --%>

        <c:forEach var="d" items="${dl}" varStatus="status">
             <li>
                <%-- el을 사용하여 객체를 지목한 후 메서드 호출할 때는 get을 제외한 나머지 이름을 애기하면 됨
                     알아서 getter를 추출해 줌(필드명)--%>
                ${status.count}. #  이름 : <span class="dancer-name">${d.name}</span>,
                                 # 크루명 : ${d.crewName},
                                 # 레벨 : ${d.danceLevel},
                                 # 페이 : ${d.danceLevel.payPerEvent}
                                 <button class="del-btn">삭제</botton>
             </li>
        </c:forEach>
    </ul>

    <a href="/register.do">새로운 댄서 등록하기</a>

    <script>

        const $dancerList = document.getElementById("dancer-list");

        $dancerList.onclick = e => {
            // 버튼 태그가 아니라면 강제 종료
            if(!e.target.matches('button')){
                return;
            }

            // 서버로 삭제 요청을 보내면서 댄서 이름을 전달
            const dancerName = e.target.previousElementSibling.textContent;
            console.log(dancerName);

            // 서버에 링크로 삭제 요청 -> 원칙적으로는 링크로 삭제 요청 x
            // 링크로 삭제 요청하면 get 요청이 들어감 -> 삭제는 post로 전달 해야함
            location.href="/delete.do?name=" + dancerName;


        }

            






    </script>



</body>
</html>