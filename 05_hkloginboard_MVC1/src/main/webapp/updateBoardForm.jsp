<%@page import="com.hk.user.dtos.BoardDto"%>
<%@page import="com.hk.user.dtos.UserDto"%>
<%@page import="com.hk.user.daos.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 조회하기</title>
<script type="text/javascript">
	function updateForm(seq){
		//수정폼으로 이동하기 -> 수정폼에 게시글 데이터는 계속 보여지게 하기 위해 상세 조회기능실행
		location.href="updateBoardForm.jsp?seq="+seq;
	}
</script>
</head>
<%
   BoardDto ddto=(BoardDto)request.getAttribute("ddto");
%>
<body>
<h1>글 수정하기</h1>
<form action="userController.jsp" method="post">
	<input type="hidden" name="command" value="updateBoard"/>
   <input type="hidden" name="seq" value="<%=ddto.getSeq()%>" />
   
   <table border="1">
      <tr>
         <th>작성자</th>
         <td><%=ddto.getId()%></td>
      </tr>
      <tr>
         <th>제목</th>
         <td><input type="text" name="title" value="<%=ddto.getTitle()%>" /></td>
      </tr>
      <tr>
         <th>내용</th>
         <td><textarea rows="10" cols="60" name="content"><%=ddto.getContent()%></textarea></td>
      </tr>
      <tr>
         <td colspan="2">
            <button type="submit">수정완료</button>
            <button type="button" onclick="location.href='userController.jsp?command=boardList'">글목록</button>
         </td>
      </tr>
   </table>
</form>
</body>
</html>


