package com.hk.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hk.user.daos.UserDao;
import com.hk.user.dtos.UserDto;

//url mapping:클라이언트에서 ~.user라고 요청하면 해당 서블릿이 실행된다
@WebServlet("*.user")
public class UserController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command=request.getRequestURI()
		           .substring(request.getContextPath().length());
		System.out.println("command값:"+command);
	      
	      UserDao dao=new UserDao();
	      
	      //로그인관리:session객체 구하기
	      HttpSession session=request.getSession();
	      
	      if(command.equals("/registForm.user")) { //회원가입폼 이동
	         dispatch("/user/registForm.jsp", request, response);
	      }else if(command.equals("/home.user")) { //index(로그인화면)이동
	         dispatch("main.jsp", request, response);
	      }else if(command.equals("/addUser.user")) {
	         String id=request.getParameter("id");
	         String name=request.getParameter("name");
	         String password=request.getParameter("password");
	         String address=request.getParameter("Address");
	         String email=request.getParameter("Email");
	         
	         boolean isS=dao.insertUser(new UserDto(id,name,password,address,email));
	         if(isS) {
	        	 request.setAttribute("회원가입성공", "home.user");
	         }else {
	        	 request.setAttribute("msg", "회원가입실패");
	        	 dispatch("error.jsp", request, response);
	         }
	      }else if(command.equals("/login.user")) {
	         String id=request.getParameter("id");
	         String password=request.getParameter("password");
	         
	         UserDto ldto=dao.getLogin(id, password);
	         if(ldto==null||ldto.getId()==null) {
	            request.setAttribute("msg", "회원이 아닙니다. 가입해주세요.");
	            dispatch("main.jsp", request, response);
	         }else {
	            //회원이면 session객체에 회원정보를 저장
	            session.setAttribute("ldto", ldto);
	            session.setMaxInactiveInterval(10*60);
	            
	            //회원등급에 따라 메인 페이지 이동
	            if(ldto.getRole().toUpperCase().equals("ADMIN")) {
	               dispatch("/user/admin_main.jsp", request, response);
	            }else if(ldto.getRole().toUpperCase().equals("MANAGER")) {
	               dispatch("manager_main.jsp", request, response);
	            }else if(ldto.getRole().toUpperCase().equals("USER")) {
	               dispatch("/user/User_main.jsp", request, response);
	            }
	         }
	      }else if(command.equals("/logout.user")) {//로그아웃
	    	  session.invalidate();
	    	  response.sendRedirect("home.user");
	      }else if(command.equals("/myinfo.user")) {//나의정보조외
	    	  String id=request.getParameter("id");
	    	  UserDto dto=dao.getUserInfo(id);
	    	  request.setAttribute("dto", dto);
	    	  dispatch("/user/userinfo.jsp", request, response);
	      }else if(command.equals("/updateUser.user")) {//나의정보ㅅ정
	    	  String id=request.getParameter("id");
	    	  String address=request.getParameter("address");
	    	  String email=request.getParameter("email");
	    	  
	    	  boolean isS=dao.updateUser(new UserDto(id,address,email));
	    	  if(isS) {
//	    /path:상위root에서 시작,		../path:현재위치에서 한단계 위		./path:현재 경로에서 시작, path:왼쪽과 동일
	    		  jsForward("수정완료", "myinfo.user?id="+id, response);
	    	  }else {
	    		  jsForward("수정실패", "error.jsp?msg=수정실패", response);
	    	  }
	      }else if(command.equals("/delUser.user")) {
	          String id=request.getParameter("id");
	          boolean isS=dao.delUser(id);
	          if(isS) {
	             jsForward("탈퇴완료", "home.user", response);
	          }else {
	             jsForward("탈퇴실패", "error.jsp?msg=탈퇴실패", response);
	          }
	      }else if(command.equals("/idChk.user")) {
	    	  String id=request.getParameter("id");
	    	  
	    	  
	    	  
	      }else if(command.equals("/getAllUserList.user")) {//회원전체 조회
	    	  List<UserDto>list=dao.getAllUserList();//회원목록 가져오기
	    	  request.setAttribute("list", list);//저장소에 list객체 저장
	    	  dispatch("/user/userAllList.jsp", request, response);
	      }else if(command.equals("/getUserList.user")) {//회원정보 조히[등급수정}
	    	  List<UserDto>list=dao.getUserList();
	    	  request.setAttribute("list", list);
	    	  dispatch("/user/userList.jsp", request, response);
	      }else if(command.equals("/roleForm.user")) {//회원등급수정폼이동
	    	  String id=request.getParameter("id");
	    	  UserDto dto=dao.getUserInfo(id);
	    	  request.setAttribute("dto", dto);
	    	  dispatch("/user/userRoleForm.jsp", request, response);
	      }else if(command.equals("/userUpdateRole.user")) {
	    	  String id=request.getParameter("id");
	    	  String role=request.getParameter("role");
	    	  boolean isS=dao.userUpdateRole(id, role);
	    	  if(isS) {
	    		  //등급수정후 등급수정폼으로 돌아간다. 이떄 id값이 포함
	    		  jsForward("등급수정성공","roleForm.user?id="+id,response);
	    	  }else {
	    		  jsForward("등급수정실패","erroe.jsp?msg=등급수정싶해",response);
	    	  }
	      }
	   }
	
	//forward구현
   public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) 
                                 throws ServletException, IOException {
      request.getRequestDispatcher(url).forward(request, response);
   }
   
   //javascript로 응답 구현
   public void jsForward(String msg, String url, HttpServletResponse response) throws IOException {
	  
	   PrintWriter out=response.getWriter();
	   String str="<script type='text/javascript'>"
   			 +	"alert('"+msg+"');"
   			 +	"location.href='"+url+"';"
   			 +	"</script>";
	   	out.print(str);
   }
}







