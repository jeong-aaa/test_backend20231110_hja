<%@page import="com.hk.user.daos.BoardDao"%>
<%@page import="com.hk.user.dtos.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.hk.user.dtos.UserDto"%>
<%@page import="com.hk.user.daos.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//1단계command값 받기 - 어떤 요청인지 확인을 위해 값을 받는다.
	String command=request.getParameter("command");

	//2.단계 DAO 객체 생성 - DB관련 작업 수행을 위해 준비...
	UserDao dao=UserDao.getUserDao();
	BoardDao ddao=new BoardDao(); //다오객체 생성
	
	if(command.equals("registForm")){//회원가입폼으로 이동
		response.sendRedirect("registForm.jsp");
	}else if(command.equals("idChk")){
		String id=request.getParameter("id");
		String resultId=dao.idCheck(id);//중복된 ID의 결과 받기(결과없으면 null)
		
		request.setAttribute("resultId", resultId);
		pageContext.forward("idChkForm.jsp");
	}else if(command.equals("addUser")){//회원가입하기
		//회원가입 폼에서 입력한 정보 받기
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		
		boolean isS=dao.insertUser(new UserDto(id,name,password,address,email));
		if(isS){
			%>
			<script type="text/javascript">
				alert("회원에 가입이 되셨습니다.");
				location.href="main.jsp";			
			</script>
			<%
		}else{
			%>
			<script type="text/javascript">
				alert("회원 가입 실패입니다.");
				location.href="userController.jsp?command=registForm";			
			</script>
			<%
		}
		
	}else if(command.equals("login")){
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
		//ID,PW에 해야하는 회원정보 조회-> 존재하면 회원 -> 로그인 실행
		UserDto dto=dao.getLogin(id, password);
		
		if(dto==null||dto.getId()==null){ //회원이 존재하지 않는 경우
			response.sendRedirect("main.jsp?msg="+URLEncoder.encode("회원가입을 해주세요", "utf-8"));
		}else{
			//회원이라면
			session.setAttribute("ldto", dto);//sessionScope에 저장(로그인정보)
			session.setMaxInactiveInterval(10*60);//10분간 요청이 없으면 세션 삭제
			
			//등급[ADMIN, MANAGER, USER]을 확인해서 해당 멩ㄴ 페이지로 이동하자
			if(dto.getRole().toUpperCase().equals("ADMIN")){
				response.sendRedirect("admin_main.jsp");
			}else if(dto.getRole().toUpperCase().equals("MANAGER")){
				
			}else if(dto.getRole().toUpperCase().equals("USER")){
				response.sendRedirect("User_main.jsp");
			}
			}
		}else if(command.equals("logout")){//로그아웃하기
			//로그아웃은 session에 로그인 정보를 삭제한다.
// 			session.removeAttribute("ldto"); //선택적으로 "ldto"라는 이름으로 저장된 객체
			session.invalidate(); //session안에 저장된 모든 정보 삭제
			System.out.println("로그아웃함");
			response.sendRedirect("main.jsp"); //request에 저장된 정보는 사라짐 / 응답하는 동작을 브라우저에게 기능을 다시시킴 서버가 jsp를 찾아
	}else if(command.equals("myinfo")){//나의 정보 조회하기
		String id=request.getParameter("id");
		UserDto dto=dao.getUserInfo(id);
		
		request.setAttribute("dto", dto);
		pageContext.forward("userinfo.jsp");
		
	}else if(command.equals("updateUser")){
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		
		//ㅎㅐ당 페이지에서 파라미터로 전달받기
		String id=request.getParameter("id");
		
// 		//session에서 ID값을 가져올 경우 : session에 저장되어있어서 어디에서든 가자ㅕ올 수 있다
// 		UserDto ldto=(UserDto)session.getAttribute("ldto");
// 		String id=ldto.getId();
		
		boolean isS=dao.updateUser(new UserDto(id,address,email));
		if(isS){
			%>
			<script type="text/javascript">
				alert("수정완료");
				location.href="userController.jsp?command=myinfo&id=<%=id%>";
			</script>
			<%
		}else{
			%>
			<script type="text/javascript">
				alert("수정실패");
				location.href="error.jsp";
			</script>
			<%
		}
	}else if(command.equals("delUser")){
		String id=request.getParameter("id");
		boolean isS=dao.delUser(id);
		if(isS){
			%>
			<script type="text/javascript">
				alert("회원탈퇴 성공!")
				location.href="main.jsp";
			</script>
			<%
		}else{
			%>
			<script type="text/javascript">
				alert("회원탈퇴 실패!")
				location.href="error.jsp";
			</script>
			<%
		}
	}else if(command.equals("getAllUserList")){//회원전체조회
		List<UserDto>list=dao.getAllUserList();
	
		request.setAttribute("list", list);
		
		pageContext.forward("userAllList.jsp");
	}else if(command.equals("getUserList")){//회원목록조회[등급수정을 위한 조회]
		List<UserDto>list=dao.getUserList();
	
		request.setAttribute("list", list);
		pageContext.forward("userList.jsp");	
	}else if(command.equals("roleForm")){//등급수정폼으로 이동
		String id=request.getParameter("id");
		UserDto dto=dao.getUserInfo(id); //나의정보조회하기 기능
		
		request.setAttribute("dto", dto);
		pageContext.forward("userRoleForm.jsp");//등급수정 폼으로 이동
	}else if(command.equals("userUpdateRole")){//등급 수정하기
		String id=request.getParameter("id");
		String role=request.getParameter("role");
		
		boolean isS=dao.userUpdateRole(id, role);
		if(isS){
			response.sendRedirect("userController.jsp?command=getUserList");
		}else{
			response.sendRedirect("error.jsp?msg"+URLEncoder.encode("등급수정실패","utf-8"));
		}
		
	}
	
	//게시판
	//3단계:command값 확인해서 분기 실행(요청분기)
	if(command.equals("boardList")){ //글목록 요청 처리
		//5단계:dao메서드 실행a
		List<BoardDto> list=ddao.getAllList();
		//6단계:Scope객체에 담기(리스트로)	
		request.setAttribute("list", list); //requestScope에 저장: "list":list
		//7단계:페이지 이동						forward:다이렉트로 주소 요청 주소가 바뀔 시간이 없음
		pageContext.forward("boardList.jsp"); //forward로 하면 최종 주소값이 아님/그 전의 주소값으로 보여짐(index.jsp)	
	}else if(command.equals("insertBoardForm")){//글추가폼으로 이동 
		response.sendRedirect("insertBoardForm.jsp");//redirect로 하면 나오는 최종 결과값 /응답할떄의 주소값으로 보여짐
	}else if(command.equals("insertBoard")){//글추가하기 실행
		//4단계:파리미터 받기
		String id=request.getParameter("id");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
	
	boolean isS=ddao.insertBoard(new BoardDto(id,title,content));
	if(isS){
			%>
			<script type="text/javascript">
				alert("글추가실패!");
				location.href="userController.jsp?command=insertBoardForm";//글추가 실패하면, 글추가 페이지로 이동하기
			</script>
			<%
	}else{
			response.sendRedirect("userController.jsp?command=boardList");
	//				pageContext.forward("hkController.jsp?command=boardList");
				%>
	<!-- 			<script type="text/javascript"> -->
	<!-- 				alert("글을 추가합니다.");
	<!-- 				location.href="hkController.jsp?command=boardList";//글추가하고, 글목록 페이지로 이동하기
	<!-- 			</script> -->
				<%
		}
	}else if(command.equals("detailBoard")){ //상세보기
		int seq=Integer.parseInt(request.getParameter("seq"));
		BoardDto dto=ddao.getBoard(seq);
		request.setAttribute("ddto", dto);
		pageContext.forward("detailBoard.jsp");
	}else if(command.equals("updateBoardForm")){//수정폼이동
		int seq=Integer.parseInt(request.getParameter("seq"));
		BoardDto dto=ddao.getBoard(seq);
		request.setAttribute("ddto", dto);
		//객체를 전달하려면 forward()로 이동해야함
		pageContext.forward("updateBoardForm.jsp");
	}else if(command.equals("updateBoard")){//수정하기
		int seq=Integer.parseInt(request.getParameter("seq"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		boolean isS=ddao.updateBoard(new BoardDto(seq,title,content));
		if(isS){
			//수정하고, 상세조회페이지로 이동
			response.sendRedirect("userController.jsp?command=detailBoard&seq="+seq);
		}else{
			response.sendRedirect("error.jsp?msg="+URLEncoder.encode("글수정 실패~","utf-8"));
		}
	}else if(command.equals("deleteBoard")){
		int seq=Integer.parseInt(request.getParameter("seq"));
		boolean isS=ddao.deleteBoard(seq);
		if(isS){
			response.sendRedirect("userController.jsp?command=boardList");
		}else{
			response.sendRedirect("error.jsp?msg="+URLEncoder.encode("글삭제ㅡ실패~","utf-8"));
		}
	}else if(command.equals("muldel")){
		//삭제를 할 글의 번호가 파라미터로 전달됨
		String[]seqs=request.getParameterValues("chk");//chk=1,2,3,4,5,6배열로 받게 하기휘해
		
		//유효값을 서버쪽(java)로 처리)에서 처리할 수 있다.
		//유효하지 않은 값ㅇㄹ 처리하려고 서버에서 작업은 함-자원낭비개념
	//	if(seqs==null||seqs.lenght==0){
			%>
	<!-- 			<script type="text/javascript"> -->
	<!--  				alert("최소 하나이상 체크하세요"); -->
	<!-- 				location.href="hkController.jsp?command=boardList"; -->
	<!-- 			</script> -->
			<%
	//}else{
		boolean isS=ddao.mulDel(seqs);
		if(isS){
			response.sendRedirect("userController.jsp?command=boardList");//boardList에는 dao가 없기때문에 controller를 들렸다 가야함
		}else{
			response.sendRedirect("error.jsp?msg="+URLEncoder.encode("여러글 삭제 실페","utf-8"));
		}
	//}
	}
	%>
</body>
</html>







