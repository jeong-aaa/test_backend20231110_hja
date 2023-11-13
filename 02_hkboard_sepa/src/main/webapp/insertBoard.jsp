<%@page import="com.hk.board.dtos.HKDto"%>
<%@page import="com.hk.board.daos.HKDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

request.setCharacterEncoding("UTF-8");
%>
<%

response.setContentType("text/html; charset=utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	//insertBoardForm.jsp에서 전달된 파라미터를 받는다.
	// id=hk, title=제목, content=내용
	String id= request.getParameter("id");
	String title=request.getParameter("title");
	String content=request.getParameter("content");
// 	if(id==""||title==""||content==""){
// 		//값을 모두 입력하라고 알리고 글추가페이지로 이동하기 코드
// 	}else{
// 		//밑에 있는 코드 실행(글추가 코드)	
// 	}
	
	//DB와 관련된 작업을 진행하려면? DAO객체 필요
	HKDao dao=new HKDao();
	boolean isS=dao.insertBoard(new HKDto(id,title,content));
	if(isS){
%>
		<script type="text/javascript">
		alert("글을 추가합니다.")
		location.href="boardList.jsp";//글추가하고, 글목록 페이지로 이동하기		
		</script>
		<%
	}else
		%>
		<script type="text/javascript">
			alert("글추가 실패!");
			location.href="insertBoardForm.jsp";//글 추가 실패하여, 글 초기페이지로 이동하기	
		
		</script>
		
		
	}
	
%>

</body>
</html>