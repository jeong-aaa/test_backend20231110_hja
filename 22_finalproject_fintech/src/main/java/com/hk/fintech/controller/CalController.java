package com.hk.fintech.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.hk.board.command.InsertCalCommand;
import com.hk.fintech.service.ICalService;
import com.hk.fintech.utils.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/schedule")
public class CalController {
   //log를 원하는 위치에 설정하여 디버깅 하기
   private static final Logger logger=LoggerFactory.getLogger(CalController.class);
   
   @Autowired
   private ICalService calService;
   
   @GetMapping(value="/calendar")
   public String calendar(Model model, HttpServletRequest request) {
      logger.info("달력보기"); 
      
      //달력에서 일일별 일정목록 구하기
//      String id = "kbj"; // 나중에 세션에서 가져온 아이디 사용     
       String year = request.getParameter("year");
       String month = request.getParameter("month");   
         
       if(year==null||month==null) {
          Calendar cal = Calendar.getInstance();
           year = cal.get(Calendar.YEAR)+"";
           month = (cal.get(Calendar.MONTH)+1)+"";
         }
      
       String yyyyMM=year+Util.isTwo(month);//202311 6자리변환
//       List<CalDto>clist=calService.calViewList(id, yyyyMM);
//       model.addAttribute("clist", clist);
      
      //달력만들기위한 값 구하기
      Map<String, Integer>map=calService.makeCalendar(request);
      model.addAttribute("calMap", map);
      
      return "thymeleaf/calboard/calendar";
   }
   
   @GetMapping(value = "/addCalBoardForm")
	public String addCalBoardForm(Model model, InsertCalCommand insertCalCommand) {
		logger.info("일정추가폼이동");
		System.out.println(insertCalCommand);
		//addCalBoardfForm 페이지에서 유효값 처리를 위해 insertCalCommand 받고 있기때문에
		model.addAttribute("insertCalCommand", insertCalCommand);
		return "thymeleaf/calboard/addCalBoardForm";
	}
 
}




