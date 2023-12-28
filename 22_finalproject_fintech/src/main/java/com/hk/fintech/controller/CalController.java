package com.hk.fintech.controller;

import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.fintech.command.InsertCalCommand;
import com.hk.fintech.dtos.AccountDto;
import com.hk.fintech.dtos.CashDto;
import com.hk.fintech.dtos.UserDto;
import com.hk.fintech.service.ICashService;
import com.hk.fintech.utils.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/schedule")
public class CalController {
   //log를 원하는 위치에 설정하여 디버깅 하기
   private static final Logger logger=LoggerFactory.getLogger(CalController.class);
   
   @Autowired
   private ICashService calService;
   
   @GetMapping(value="/calendar")
   public String calendar(Model model, HttpServletRequest request) {
      logger.info("달력보기"); 
      
      //달력에서 일일별 일정목록 구하기
//      String id = "kbj"; // 나중에 세션에서 가져온 아이디 사용  
//       String email = "112@123";
       String year = request.getParameter("year");
       String month = request.getParameter("month"); 
      
       HttpSession session=request.getSession();
	   UserDto ldto=(UserDto)session.getAttribute("ldto");
	   model.addAttribute("ldto", new UserDto());
       
	   String email = ldto.getUseremail();
	   
         
       if(year==null||month==null) {
          Calendar cal = Calendar.getInstance();
           year = cal.get(Calendar.YEAR)+"";
           month = (cal.get(Calendar.MONTH)+1)+"";
          
         }
      
       String yyyyMM=year+Util.isTwo(month);//202311 6자리변환
       List<CashDto>clist=calService.Cash(email, yyyyMM);
       model.addAttribute("clist", clist);
      
      //달력만들기위한 값 구하기
      Map<String, Integer>map=calService.makeCalendar(request);
      model.addAttribute("calMap", map);
      
//      System.out.println(clist.get(0));
      return "thymeleaf/calboard/calendar";
   }
   
//   @GetMapping(value = "/addCalBoardForm")
//	public String addCalBoardForm(Model model, InsertCalCommand insertCalCommand) {
//		logger.info("일정추가폼이동");
//		System.out.println(insertCalCommand);
//		
//		HttpSession session=request.getSession();
//	    UserDto ldto=(UserDto)session.getAttribute("ldto");
//		model.addAttribute("ldto", new UserDto());
////		
//		model.addAttribute("insertCalCommand", insertCalCommand);
//		return "thymeleaf/calboard/addCalBoardForm";
//	}
// 
  
  @PostMapping(value = "/addCalBoard")
	public String addCalBoard(@Validated InsertCalCommand insertCalCommand,
							  BindingResult result, Model model) throws Exception {
		logger.info("일정추가하기");
	      
	      
		System.out.println(insertCalCommand);
		if(result.hasErrors()) {
			System.out.println("글을 모두 입력해야 함");
			return "thymeleaf/calboard/calendar";
		}
		
		calService.insertCalBoard(insertCalCommand);
		
		return "redirect:/schedule/calendar?year="+insertCalCommand.getYear()
										+"&month="+insertCalCommand.getMonth();
	}
  
  
  	@GetMapping(value = "/TransactionDataList")
	public String TransactionDataList(@RequestParam Map<String, String>map
							, HttpServletRequest request
							, Model model) {
		logger.info("일정목록보기");
//		HttpSession session=request.getSession();
//		String id=session.getAttribute("id");
//		String id="kbj";//임시로 id 저장
		
		//command 유효값 처리를 위해 기본 생성해서 보내줌
//		model.addAttribute("deleteCalCommand", new DeleteCalCommand());
		
		//일정목록을 조회할때마다 year, month, date를 세션에 저장
		HttpSession session=request.getSession();
		
		if(map.get("year")==null) {
			//조회한 상태이기때문에 ymd가 저장되어 있어서 값을 가져옴
			map=(Map<String, String>)session.getAttribute("ymdMap");
		}else {
			//일정을 처음 조회했을때 ymd를 저장함
			session.setAttribute("ymdMap", map);
		}
		
		//달력에서 전달받은 파라미터 year, month, date를 8자리로 만든다.
		String yyyyMMdd=map.get("year")
				       +Util.isTwo(map.get("month"))
				       +Util.isTwo(map.get("date"));
		List<AccountDto> list= calService.TransactionDataList(email, yyyyMMdd);
		model.addAttribute("list", list);
		
		return "thymeleaf/calboard/calBoardList";
	}
}




