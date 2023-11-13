<%@include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head> 
<body>
<div id="container">
   <div class="main">
      <div class="lead">
         <span>${ldto.id}[${ldto.role}]님이 로그인 함</span> |
         <span><a href="getAllUserList.do">회원전체조회</a></span> |
         <span><a href="getUserList.do">회원정보[등급]수정</a></span> |
         <span><a href="logout.do">로그아웃</a></span>
      </div>
      <div class="contents">
         <h1>관리자 페이지</h1>
      </div>
   </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>

