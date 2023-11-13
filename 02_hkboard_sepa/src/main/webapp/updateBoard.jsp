<%@page import="com.hk.board.dtos.HKDto"%>
<%@page import="com.hk.board.daos.HKDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

request.setCharacterEncoding("utf-8");
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

   //updateBoardForm.jsp에서 전달된 파라미터 받기: seq, title, content
   String seq=request.getParameter("seq");
   int sseq=Integer.parseInt(seq);
   String title=request.getParameter("title");
   String content=request.getParameter("content");
   
   HKDao dao=new HKDao();
   boolean isS=dao.updateBoard(new HKDto(sseq,title,content));
   if(isS){
%>
      <script type="text/javascript">
         alert("글을 수정합니다.");
         location.href="detailBoard.jsp?seq=<%=seq%>";
      </script>
      <%
   }else{
      %>
      <script type="text/javascript">
         alert("글을 수정 실패!");
         location.href="updateBoardForm.jsp?seq=<%=seq%>";
      </script>
      <%
   }
%>
</body>
</html>





