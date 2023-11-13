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
	String command=request.getParameter("command");

	UserDao dao=UserDao.getUserDao();//클래스명.메서드():static메서드 호출방법
	
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
%>
</body>
</html>







