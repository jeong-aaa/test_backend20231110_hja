<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 글 작성하기</title>
<link rel="stylesheet" href="css/board2.css"/>
</head>
<body>
<h1>새 글 작성하기</h1>
<form action="userController.jsp" method="post"> <!--controller로 이동 -->
<input class="insert" type="hidden" name="command" value="insertBoard">  <!--사용자가 볼필요 없음-->
	<table border="1" class="table1">
		<tr>
			<th>작성자(ID)</th>
			<td><input type="text" name="id" required="required"/></td>
		</tr>
		<tr>
			<th>글제목</th>
			<td><input type="text" name="title" required="required"/></td>
		</tr>
		<tr>
			<th>글내용</th>
			<td><textarea rows="10" cols="108" name="content" required="required"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
			<input style="width: 50px; text-align: left;" type="submit" value="등록" />
			<input style="width: 60px; text-align: left;" type="button" value="글목록"
						onclick="location.href='userController.jsp?command=boardList'" /> <!--글목록으로 돌아가는 부분-->

			</td>
		</tr>
	</table>
</form>
</body>
</html>