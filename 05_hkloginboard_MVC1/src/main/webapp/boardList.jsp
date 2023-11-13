<%@include file="header.jsp" %>
<%@page import="com.hk.user.dtos.BoardDto"%>
<%@page import="com.hk.user.dtos.UserDto"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.user.dtos.UserDto"%>
<%@page import="com.hk.user.daos.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록 조회</title>
<link rel="stylesheet" href="css/board.css" />
<script type="text/javascript">
	function insertBoardForm(){
		location.href="userController.jsp?command=insertBoardForm"; //글 추가할떄 요청하는(redirect)
	}
	//전체선택박스 구현
	function allSel(bool){
		var chks=document.getElementsByName("chk");//[chk,chk,chk,..]
		for(var i=0;i<chks.length;i++){
			chks[i].checked=bool;//true면 체크, false 체크해제
		}
	}
	
	//체크박스의 체크여부를 확인하고, submit 실행하기
	//true를 반환하면 submuit, false submit X
	function isAllCheck(){
		if(confirm("정말 삭제할거야?")){
			var count=0;
			var chks=documnet.getElementsByName("chk");//[chk,chk,chk,...]
			for(var i = 0; i < chks.length; i++) {
					if(chks[i].checked){//체크여부확인:체크되며 ㄴtrue
						count++;
						}
					}
					if(count==0){
						alert("최소하나 이상 체크하세요")
					}
					return count>0?true:false;
			}
			return false;
	}
	
	
</script>
</head>
<%//실행부: java 코드를 실행하는 영역 
	List<BoardDto> list2 =(List<BoardDto>)request.getAttribute("list");
%>
<body>
<h1>게시판</h1>
<h2>글목록</h2>
<form action="userController.jsp" method="post" onsubmit="return isAllCheck()">
<input type="hidden" name="command" value="muldel"/>
<table border="1">
	<col width="30px" />
	<col width="50px" />
	<col width="100px" />
	<col width="300px" />
	<col width="200px" />
	<tr>
		<th><input type="checkBox" name="all" onclick="allSel(this.checked)"/></th><!--전체선택체크박스-->
		<th>seq</th><th>작성자</th><th>제목</th><th>작성일</th>
	</tr>
			<%
	for(int i=0;i<list2.size();i++){
		BoardDto ddto=list2.get(i);// list[dto,dto,..]--> dto꺼내기
			%> <%-- //html영역 --%>
			<tr>
				<td><input type="checkbox" name="chk" value="<%=ddto.getSeq()%>"/><!--체크박스 -->
				<td><%=ddto.getSeq()%></td>
				<td><%=ddto.getId()%></td>
				<td><a href="userController.jsp?command=detailBoard&seq=<%=ddto.getSeq()%>"><%=ddto.getTitle()%></a></td>
				<td><%=ddto.getRegDate()%></td>
			</tr>
			<%
		}
	%>
	<tr>
		<td colspan="5">
			<button type="button" onclick="insertBoardForm()">글추가</button>
			<input style="width: 45px; text-align: left;" type="submit" value="삭제"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>



