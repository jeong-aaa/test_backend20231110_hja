package com.hk.fintech.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hk.fintech.command.InsertCalCommand;
import com.hk.fintech.dtos.AccountDto;
import com.hk.fintech.dtos.CashDto;
import com.hk.fintech.mapper.AccountMapper;
import com.hk.fintech.mapper.CashMapper;
import com.hk.fintech.utils.Util;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CashServiceImp implements ICashService{

   @Autowired
   private CashMapper cashMapper;
   
   @Autowired
   private AccountMapper accountMapper;
   
   public Map<String, Integer> makeCalendar(HttpServletRequest request){
      Map<String ,Integer> map=new HashMap<>();
      
      //달력의 날짜를 바꾸기 위해 전달된 year와 month 파라미터를 받는 코드
      String paramYear=request.getParameter("year");
      String paramMonth=request.getParameter("month");
      
      Calendar cal=Calendar.getInstance(); // 추상클래스이고, static 메서드 new(X)
      
      int   year=(paramYear==null)?cal.get(Calendar.YEAR):Integer.parseInt(paramYear) ;
      int   month=(paramMonth==null)?cal.get(Calendar.MONTH)+1:Integer.parseInt(paramMonth) ;                  
      
      //                          기본 오늘날짜로 저장할지  :  요청된 날짜로 저장할지
      //                         calendar객체에서 month는 0~11월임
      
      // 11월,12월,13월.....      오류 처리
      // -2월, -1월 , 0월 , 1월   오류 처리
      if(month>12) {
         month=1;
         year++;
      }
      if(month<1) {
         month=12;
         year--;
      }
      
      //1.월의 1일에 대한 요일 구하기
      cal.set(year, month-1,1);// 원하는 날짜로 셋팅
      int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);//1~7중에 반환(1:일요일~7:토요일)
      
      //2.월의 마지막 날 구하기
      int lastDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
      
      map.put("year", year);
      map.put("month", month);
      map.put("dayOfWeek", dayOfWeek);
      map.put("lastDay", lastDay);
      
      return map;
   }
   
   @Override
   public boolean insertCalBoard(InsertCalCommand insertCalCommand) {
      // command --> dto로  값을 이동
      // DB에서는 mdate 컬럼 , command에서는 year, month... : 12자리로 변환작업
      String mdate=insertCalCommand.getYear()
                +Util.isTwo(insertCalCommand.getMonth()+"")
                +Util.isTwo(insertCalCommand.getDate()+"");
      // 202311151335 12자리
      // 20231181110  11자리....ㅜㅜ
      
      //command --> dto 값 복사 
      CashDto dto=new CashDto();
      dto.setUseremail(insertCalCommand.getUseremail());
      dto.setContent(insertCalCommand.getContent());
      dto.setMio(insertCalCommand.getMio());
      dto.setMoney(insertCalCommand.getMoney());
      dto.setMdate(mdate);
        
      int count=cashMapper.addCash(dto);
      
      return count>0?true:false;
   }
//
//   @Override
//   public List<CalDto> checkinfoBydept(String yyyyMMdd ){
////   public List<CalDto> calBoardList( String yyyyMMdd) {
//      System.out.println("service:"+yyyyMMdd);
//      return calMapper.checkinfoBydept(yyyyMMdd);
//   }
//
//   @Override
//   public CalDto calBoardDetail(int seq) {
//      return calMapper.calBoardDetail(seq);
//   }
//
//   @Override
//   public boolean calBoardUpdate(UpdateCalCommand updateCalCommand) {
//      //command:year,month,date.. ---> dto: mdate
//      String mdate=updateCalCommand.getYear()
//             +Util.isTwo(updateCalCommand.getMonth()+"")
//             +Util.isTwo(updateCalCommand.getDate()+"")
//             +Util.isTwo(updateCalCommand.getHour()+""); // 12자리
//      
//      //dto <---command값
//      CalDto dto=new CalDto();
//      dto.setSeq(updateCalCommand.getSeq());
////      dto.setTitle(updateCalCommand.getTitle());
//      dto.setContent(updateCalCommand.getContent());
//      dto.setMdate(mdate);
//      
//      return calMapper.calBoardUpdate(dto);
//   }
//
//   @Override
//   public boolean calMulDel(Map<String, String[]> map) {
//      return calMapper.calMulDel(map);
//   }
//
   @Override
   public List<CashDto> Cash(String email, String yyyyMM) {
	   
	   Map<String ,String> map=new HashMap<>();
	   
	   map.put("useremail",email);
	   map.put("yyyyMM",yyyyMM);
	  
	      
      return cashMapper.Cash(map);
   }
   
   @Override
   public List<AccountDto> Account(String email, String yyyyMM) {
	   
	   Map<String ,String> map=new HashMap<>();
	   
	   map.put("useremail",email);
	   map.put("yyyyMM",yyyyMM);
	  
	      
      return accountMapper.Account(map);
   }
//
//   @Override
//   public int calBoardCount(String yyyyMMdd) {
//      // TODO Auto-generated method stub
//      return calMapper.calBoardCount(yyyyMMdd);
//   }
//   
//
   @Override
   public List<CashDto> cashsum(String email) {
	 
      return cashMapper.cashsum(email);
      
   }

	@Override
	public List<CashDto> cashDetailList(String email,String yyyyMMdd) {
		Map<String, String>map=new HashMap<String, String>();
		map.put("useremail", email);
		map.put("mdate", yyyyMMdd);
		return cashMapper.cashDetailList(map);
	}
	
	@Override
	public List<AccountDto> Accountsum(String email) {
		 
	      return accountMapper.Accountsum(email);
	      
	   }

}