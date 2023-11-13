package com.hk.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//DispatcherSerclet 객체가 @Controller로 선언된 클래스를 찾는다.
//"home.do"라고 요청했으면, 매핑되어 있는 메서드를 실행한다.
@Controller
public class HomeController {
	
//	@GeMapping
//	@PostMapping
//	@PutMapping
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)	
	public String home(Model model,HttpServletRequest request,
									HttpServletRequest response) {
		System.out.println("home 요청");
		String str="Hello Spring";
		
//		request.setAttribute("str",str);
		model.addAttribute("str",str);
		
//		//우리가 사용하던 sendRedirect() 기능임
//		return "redirect:home.jsp?seq=5&id=hk";
		//우리가 사용하던 forward()기능임
		return "home";//ViewResolver가 만들어줌:"WUB-INF/views/"+""home"+".jsp"
	}
	
	@RequestMapping(value = "/boardList.do", method = RequestMethod.GET)
	public String boardList() {
		
		return "";
	}
}
