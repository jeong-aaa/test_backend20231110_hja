package com.hk.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.board.command.AddUserCommand;
import com.hk.board.command.LoginCommand;
import com.hk.board.command.UpdateBoardCommand;
import com.hk.board.command.UpdateUserCommand;
import com.hk.board.dtos.BoardDto;
import com.hk.board.dtos.MemberDto;
import com.hk.board.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;


@Controller
@RequestMapping(value = "/user")
public class MemberController {

   @Autowired
   private MemberService memberService;
   
   @GetMapping(value = "/addUser")
   public String addUserForm(Model model) {
      System.out.println("회원가입폼 이동");
      
      //회원가입폼에서 addUserCommand객체를 사용하는 코드가 작성되어 있어서 
      //null일경우 오류가 발생하기때문에 보내줘야 함
      model.addAttribute("addUserCommand", new AddUserCommand());
      
      return "member/addUserForm";
   }

   
   @PostMapping(value = "/addUser")
   public String addUser(@Validated AddUserCommand addUserCommand
                      ,BindingResult result,Model model) {
      System.out.println("회원가입하기");
      
      if(result.hasErrors()) {
         System.out.println("회원가입 유효값 오류");
         return "member/addUserForm";
      }
      
      try {
         memberService.addUser(addUserCommand);
         System.out.println("회원가입 성공");
         return "redirect:/";
      } catch (Exception e) {
         System.out.println("회원가입실패");
         e.printStackTrace();
         return "redirect:addUser";
      }

   }
   
   @ResponseBody
   @GetMapping(value = "/idChk")
   public Map<String,String> idChk(String id){
      System.out.println("ID중복체크");
      
      String resultId=memberService.idChk(id);
      // json객체로 보내기 위해 Map에 담아서 응답
      // text라면 그냥 String으로 보내도 됨
      Map<String,String>map=new HashMap<>();
      map.put("id", resultId);
      return map;
   }
   
   
   //로그인 폼 이동
   @GetMapping(value = "/login")
   public String loginForm(Model model) {
      model.addAttribute("loginCommand", new LoginCommand());
      return "member/login";
   }
   
   //로그인 실행
   @PostMapping(value = "/login")
   public String login(@Validated LoginCommand loginCommand
                    ,BindingResult result
                    ,Model model
                    ,HttpServletRequest request) {
      if(result.hasErrors()) {
         System.out.println("로그인 유효값 오류");
         return "member/login";
      }
      
      String path=memberService.login(loginCommand, request, model);
      
      return path;
   }
   
   @GetMapping(value="/logout")

   public String logout(HttpServletRequest request) {
      System.out.println("로그아웃");
      request.getSession().invalidate();
      return "redirect:/";
   }
   
   //개인정보 폼 이동
   @GetMapping(value = "/userinfo")
   public String user(HttpServletRequest request,  Model model) {
	   
	  HttpSession session=request.getSession();
	  MemberDto mdto=(MemberDto)session.getAttribute("mdto");
	   
	   MemberDto dto = memberService.getUser(mdto.getId());
	   
	   model.addAttribute("updateUserCommand", new UpdateUserCommand());
		// 출력용
		model.addAttribute("dto", dto);

	   
      return "member/userinfo";
   }
   

   
   //개인정보 수정 
   @PostMapping(value = "/userinfo")
   public String userinfo(@Validated UpdateUserCommand updateUserCommand,BindingResult result) {
	  
	   memberService.updateUser(updateUserCommand);
		System.out.println("수정");
	   return "redirect:/user/userinfo";

	     
	}
   
//   @PostMapping(value = "/boardUpdate")
//	public String boardUpdate(@Validated UpdateBoardCommand updateBoardCommand, BindingResult result) {
//
//		if (result.hasErrors()) {
//			System.out.println("수정내용을 모두 입력하세요");
//			return "board/boardDetail";
//		}
//
//		boardService.updateBoard(updateBoardCommand);
//
//		return "redirect:/board/boardDetail?board_seq=" + updateBoardCommand.getBoard_seq();
//		
//		?board_seq=" + updateBoardCommand.getBoard_seq();
//	}
   
   
   
   
//   @PostMapping(value = "/addUser")
//   public String addUser(@Validated AddUserCommand addUserCommand
//                      ,BindingResult result,Model model) {
//      System.out.println("회원가입하기");
//      
//      if(result.hasErrors()) {
//         System.out.println("회원가입 유효값 오류");
//         return "member/addUserForm";
//      }
//      
//      try {
//         memberService.addUser(addUserCommand);
//         System.out.println("회원가입 성공");
//         return "redirect:/";
//      } catch (Exception e) {
//         System.out.println("회원가입실패");
//         e.printStackTrace();
//         return "redirect:addUser";
//      }
//
//   }

   
}