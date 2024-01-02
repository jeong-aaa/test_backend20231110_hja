package com.hk.fintech.controller;

import java.net.HttpURLConnection;
import java.text.DecimalFormat;
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
import com.hk.fintech.service.AccountService;
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
   @Autowired
   private AccountService accountService;
   
   
   
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
      
      
      if (year == null || month == null) {
    	    Calendar cal = Calendar.getInstance();
    	    year = String.valueOf(cal.get(Calendar.YEAR));
    	    month = String.valueOf(cal.get(Calendar.MONTH) + 1);
    	} else {
    	    int intMonth = Integer.parseInt(month);
    	    int intYear = Integer.parseInt(year);

    	    if (intMonth < 1) {
    	        intMonth = 12;
    	        intYear--;
    	    } else if (intMonth > 12) {
    	        intMonth = 1;
    	        intYear++;
    	    }

    	    year = String.valueOf(intYear);
    	    month = String.valueOf(intMonth);
    	}

         
//       if(year==null||month==null) {
//          Calendar cal = Calendar.getInstance();
//           year = cal.get(Calendar.YEAR)+"";
//           month = (cal.get(Calendar.MONTH)+1)+"";
//          
//         }
      
       DecimalFormat df = new DecimalFormat("###,###");
       
       String yyyyMM=year+Util.isTwo(month);
       List<CashDto>clist=calService.Cash(email, yyyyMM);
       model.addAttribute("clist", clist);
       
//       수입지출 컬러로
       int insum = 0;
       int outsum = 0;
       for (CashDto cashDto : clist) {
          if (cashDto.getMio().equals("수입"))
             insum+=cashDto.getMoney();
          else {
             outsum+=cashDto.getMoney();
          }
       }

       model.addAttribute("insum", insum+"");
       model.addAttribute("outsum", outsum+"");
       
//       입출금
       List<AccountDto>alist=calService.Account(email, yyyyMM);
       model.addAttribute("alist", alist);
       
//       입금출금 컬러로
       int incomesum = 0;
       int outcomesum = 0;
       for (AccountDto accountDto : alist) {
          int amount = accountDto.getTran_amt();
           if (accountDto.getInout_type().equals("입금")) {
        	   incomesum += amount;
           } else {
        	   outcomesum += amount;
           }
       }
       model.addAttribute("incomesum", incomesum+"");
       model.addAttribute("outcomesum", outcomesum+"");
      
       int totalinSum = insum + incomesum;
       String totalinsum = df.format(totalinSum);
       model.addAttribute("totalinsum", totalinsum + "");
       System.out.println("총 수입: "+totalinsum);
       
       int totaloutSum = outsum + outcomesum;
       String totaloutsum = df.format(totaloutSum);
       model.addAttribute("totaloutsum", totaloutsum + "");
       System.out.println("총 지출: "+totaloutSum);
       
//       총합계산
       int total = totalinSum - totaloutSum;
       String totalsum = df.format(total);
       model.addAttribute("totalsum", totalsum + "");
       System.out.println("총 합: "+totalsum);
       
//       System.out.println(clist.get(0));
       
      //달력만들기위한 값 구하기
      Map<String, Integer>map=calService.makeCalendar(request);
      model.addAttribute("calMap", map);
      
//      System.out.println(clist.get(0));
      return "thymeleaf/calboard/calendar";
   }
   
//   @GetMapping(value = "/addCalBoardForm")
//   public String addCalBoardForm(Model model, InsertCalCommand insertCalCommand) {
//      logger.info("일정추가폼이동");
//      System.out.println(insertCalCommand);
//      
//      HttpSession session=request.getSession();
//       UserDto ldto=(UserDto)session.getAttribute("ldto");
//      model.addAttribute("ldto", new UserDto());
////      
//      model.addAttribute("insertCalCommand", insertCalCommand);
//      return "thymeleaf/calboard/addCalBoardForm";
//   }
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
     @ResponseBody
   public Map<String, List<AccountDto>> TransactionDataList(@RequestParam Map<String, String>map
                     , HttpServletRequest request
                     , Model model) {
      logger.info("거래내역상세-계좌");
//      HttpSession session=request.getSession();
//      String id=session.getAttribute("id");
//      String id="kbj";//임시로 id 저장
      
      //일정목록을 조회할때마다 year, month, date를 세션에 저장
      HttpSession session=request.getSession();
      UserDto ldto=(UserDto)session.getAttribute("ldto");
      String email=ldto.getUseremail();
      
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
      System.out.println("yyyyMMdd:"+yyyyMMdd);
      Map<String, List<AccountDto>> map2 = new HashMap<>();
      List<AccountDto> list= accountService.TransactionDataList(email, yyyyMMdd);
      map2.put("list", list);
      System.out.println("list.size:"+list.size());
      return map2;
   }
     
     @GetMapping(value = "/cashDetailList")
     @ResponseBody
     public Map<String, List<CashDto>> cashDetailList(@RequestParam Map<String, String>map
                     , HttpServletRequest request
                     , Model model) {
      logger.info("거래내역상세-현금");
//      HttpSession session=request.getSession();
//      String id=session.getAttribute("id");
//      String id="kbj";//임시로 id 저장
      
      //일정목록을 조회할때마다 year, month, date를 세션에 저장
      HttpSession session=request.getSession();
      UserDto ldto=(UserDto)session.getAttribute("ldto");
      String email=ldto.getUseremail();
      
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
      System.out.println("yyyyMMdd:"+yyyyMMdd);
      Map<String, List<CashDto>> map2 = new HashMap<>();
      List<CashDto> list= calService.cashDetailList(email, yyyyMMdd);
      map2.put("list", list);
      System.out.println("list.size:"+list.size());
      return map2;
   }
}
