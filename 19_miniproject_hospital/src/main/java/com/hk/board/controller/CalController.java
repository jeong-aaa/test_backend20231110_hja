package com.hk.board.controller;

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

import com.hk.board.command.DeleteCalCommand;
import com.hk.board.command.InsertCalCommand;
import com.hk.board.command.UpdateCalCommand;

import com.hk.board.dtos.CalDto;


import com.hk.board.service.ICalService;
import com.hk.board.utils.Util;

import com.hk.board.controller.CalController;

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
   public String calendar( Model model, HttpServletRequest request) {
      logger.info("달력보기"); 
    
  
       String year = request.getParameter("year");
       String month = request.getParameter("month");   
         
       if(year==null||month==null) {
          Calendar cal = Calendar.getInstance();
           year = cal.get(Calendar.YEAR)+"";
           month = (cal.get(Calendar.MONTH)+1)+"";
         }
       String yyyyMM=year+Util.isTwo(month);//202311 6자리변환
       System.out.println("year:"+year+","+month);
       List<CalDto>clist=calService.getAllList(yyyyMM);
       model.addAttribute("clist", clist);
      for (CalDto calDto : clist) {
    	  System.out.println(calDto);		
	}
      //달력만들기위한 값 구하기
      Map<String, Integer>map=calService.makeCalendar(request);
      model.addAttribute("calMap", map);
      logger.info("달력보기:calMap"); 
      return "/calboard/calendar";
   }
   
   @GetMapping(value = "/addCalBoardForm")
	public String addCalBoardForm(Model model, InsertCalCommand insertCalCommand) {
		logger.info("일정추가폼이동");
		System.out.println(insertCalCommand);
		//addCalBoardfForm 페이지에서 유효값 처리를 위해 insertCalCommand 받고 있기때문에
		model.addAttribute("insertCalCommand", insertCalCommand);
		return "/calboard/addCalBoardForm";
	}
   
   @PostMapping(value = "/addCalBoard")
	public String addCalBoard(@Validated InsertCalCommand insertCalCommand,
							  BindingResult result) throws Exception {
		logger.info("일정추가하기");
		System.out.println(insertCalCommand);
		if(result.hasErrors()) {
			System.out.println("글을 모두 입력해야 함");
			return "/calboard/addCalBoardForm";
		}
		
		calService.insertCalBoard(insertCalCommand);
		
		return "redirect:/schedule/calendar?year="+insertCalCommand.getYear()
										+"&month="+insertCalCommand.getMonth();
	}
   
   @GetMapping(value = "/calBoardList")
   public String calBoardList(@RequestParam Map<String, String>map
                     , HttpServletRequest request
                     , Model model) {
      logger.info("일정목록보기");
    
      //command 유효값 처리를 위해 기본 생성해서 보내줌
      model.addAttribute("deleteCalCommand", new DeleteCalCommand());
      
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
//      List<CalDto> list= calService.calBoardList(yyyyMMdd);
      System.out.println(yyyyMMdd);
        List<CalDto> list= calService.checkinfoBydept(yyyyMMdd);
      System.out.println(list.size());
      model.addAttribute("list", list);
      
      return "/calboard/calBoardList";
   }
   
   @PostMapping(value = "/calMulDel")
   public String calMulDel(@Validated DeleteCalCommand deleteCalCommand,
                     BindingResult result,
                     HttpServletRequest request,
                     Model model) {
      
      if(result.hasErrors()) {
         System.out.println("최소 하나 이상 체크하기");
         
         HttpSession session=request.getSession();
//         String id=session.getAttribute("id");
         
         
         //session에 저장된 ymd 값은 목록 조회할때 추가되는 코드임
         Map<String, String>map=(Map<String, String>)session.getAttribute("ymdMap");
         
         //달력에서 전달받은 파라미터 year, month, date를 8자리로 만든다.
         String yyyyMMdd=map.get("year")
                      +Util.isTwo(map.get("month"))
                      +Util.isTwo(map.get("date"));
         List<CalDto> list= calService.getAllList(yyyyMMdd);
         model.addAttribute("list", list);
         return "/calboard/calBoardList";
      }
      Map<String,String[]>map=new HashMap<>();
      map.put("seqs", deleteCalCommand.getSeq());
      calService.calMulDel(map);
      
      return "redirect:/schedule/calBoardList";
   }
   
   @GetMapping("/calMulDel")
   public String calDel(String[] seq) {
      logger.info("일정삭제하기");
      System.out.println(seq[0]);
      Map<String,String[]>map=new HashMap<>();
      map.put("seqs", seq); 
      calService.calMulDel(map);
      return "redirect:/schedule/calBoardList";
   }
   
   @GetMapping(value = "/calBoardDetail")
   public String calBoardDetail(UpdateCalCommand updateCalCommand, int seq, Model model) {
      logger.info("일정상세보기");
      
      CalDto dto=calService.calBoardDetail(seq);
      System.out.println(dto);
      //dto ---> command
      updateCalCommand.setSeq(dto.getSeq());
      updateCalCommand.setDeptno(dto.getDeptDto().getDeptname());
      updateCalCommand.setContent(dto.getContent());
      updateCalCommand
                .setYear(Integer.parseInt(dto.getMdate().substring(0, 4)));
      updateCalCommand
                .setMonth(Integer.parseInt(dto.getMdate().substring(4, 6)));
      updateCalCommand
                .setDate(Integer.parseInt(dto.getMdate().substring(6, 8)));
      updateCalCommand
                .setHour(Integer.parseInt(dto.getMdate().substring(8, 10)));

      model.addAttribute("updateCalCommand", updateCalCommand);
      
      return "/calboard/calBoardDetail";
   }
   
   @PostMapping(value = "/calBoardUpdate")
   public String calBoardUpdate(@Validated UpdateCalCommand updateCalCommand, int seq
                        ,BindingResult result
                        ,Model model) {
      logger.info("일정 수정하기");
      
      if(result.hasErrors()) {
         System.out.println("수정할 목록을 확인하세요");
         return "/calboard/calBoardDetail";
      }
      
      calService.calBoardUpdate(updateCalCommand);
      
      return "redirect:/schedule/calBoardDetail?seq="+updateCalCommand.getSeq();      

   }
   
   @ResponseBody
   @GetMapping(value = "calCountAjax")
   public Map<String, Integer> calCountAjax(String yyyyMMdd){
//      logger.info("일정개수");
      Map<String, Integer>map=new HashMap<>();
     
      int count=calService.calBoardCount(yyyyMMdd);
      map.put("count", count);
      return map;
   }
}




