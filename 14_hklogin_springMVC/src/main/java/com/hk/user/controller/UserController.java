package com.hk.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.user.dtos.UserDto;
import com.hk.user.service.IHkService;

@Controller
public class UserController {

	@Autowired
	private IHkService hkService;
	
	@RequestMapping(value="/main.do", method=RequestMethod.GET)
	public String main(Model model) {
		System.out.println("main.do요청");
		
		return "main";
	}
	
	@RequestMapping(value="/registForm.do", method= {RequestMethod.POST, RequestMethod.GET})
	public String registForm(Model model) {
	      System.out.println("registForm.do요청");

	      return "user/registForm";  
	}
	@RequestMapping(value="/getLogin.do", method= {RequestMethod.POST, RequestMethod.GET})
	public String getLogin(Model model, UserDto dto) {
	      System.out.println("getLogin.do요청");
	      
	      UserDto ldto=hkService.getLogin(dto);
	      if(ldto==null||ldto.getId()==null) {
	    	  return "redirect:User_main.do";      	  
	      	}else {
	      		model.addAttribute("msg", "실패");
	      		return "user/User_main";
	      }  
	}
	
	@RequestMapping(value="/addUser.do", method= {RequestMethod.POST, RequestMethod.GET})
	public String addUser(Model model, UserDto dto) {
	      System.out.println("addUser.do요청");
	      
	      boolean isS=hkService.insertUser(dto);
	      if(isS) {
	    	  return "redirect:main.do";      	  
	      }else {
	    	  model.addAttribute("msg", "실패");
	    	  return "user/registForm";
	      }

	}
	
	@ResponseBody
	@RequestMapping(value="/idChk.do", method= {RequestMethod.POST, RequestMethod.GET})
	public String idChk(Model model, UserDto dto) {
		System.out.println("idChk.do요청");
		
		UserDto ddto = hkService.idCheck(dto);
	      
	    // model.addAttribute("resultId", dto2.getId());
	    return ddto == null ? "null": ddto.getId(); 
	}

	//로그인
	@RequestMapping(value="/userList.do", method= {RequestMethod.POST, RequestMethod.GET})
	public String userList(Model model, UserDto dto) {
		
		System.out.println("userList.do요청");
		boolean isS=hkService.insertUser(dto);
		if(isS) {
			return "redirect:user/User_main.do";
		}else {
			return "user/admin_main.do";
		}
	}
	@RequestMapping(value="/userAllList.do", method= {RequestMethod.POST, RequestMethod.GET})
	public String userAllList(Model model) {
		System.out.println("userAllList.do요청");
		
		return "user/userAllList";
	}
}
	
	
	
	
	
	
	
	
	
