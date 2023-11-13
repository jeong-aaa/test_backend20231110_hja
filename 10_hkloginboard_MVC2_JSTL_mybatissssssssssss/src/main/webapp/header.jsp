<%--캐쉬를 남기지 않게 하는 4 --%>
<%
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setDateHeader("Expires", 0L); // Do not cache in proxy server
%>
<%@page import="com.hk.board.dtos.HkDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="css/layoutt.css" />
</head>
<%
//    UserDto ldto=(UserDto)session.getAttribute("ldto");

// 	//로그인 정보가 없는 경우 화면 처리 --> 로그인 정보가 null인 경우 오류 발생하기 때문
// 	if(ldto==null){
// 		pageContext.forward("main.jsp");
// 	}
%>
<body>
<c:if test="${sessionScope.ldto==null}">
	<jsp:forward page="main.jsp"/>
</c:if>
<nav class="navbar">
	<div id="navbar">
	<ul class="navbar-nav">
      	<c:choose>
      		<c:when test="${sessionScope.ldto.role eq 'ADMIN'}">
      			<li><a href="user/admin_main.jsp">HOME</a></li>
      		</c:when>
      		<c:when test="${sessionScope.ldto.role eq 'USER'}">
      			<li><a href="user/User_main.jsp">HOME</a></li>
      		</c:when>
      	</c:choose>
		<li>ABOUT</li>
		<c:choose>
			<c:when test="${sessionScope.ldto.role eq 'ADMIN'}">
      			<li><a href="index.jsp">CONTECT</a></li>
      		</c:when>
      		<c:when test="${sessionScope.ldto.role eq 'USER'}">
      			<li><a href="index.jsp">CONTECT</a></li>
   			</c:when>
   		</c:choose>
	</ul>
	</div>
</nav>
</body>
</html>





