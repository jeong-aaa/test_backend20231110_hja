package com.hk.fintech.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.tags.shaded.org.apache.xpath.operations.Equals;
import org.springframework.stereotype.Component;

import com.hk.fintech.dtos.AccountDto;
import com.hk.fintech.dtos.CashDto;



@Component
public class Util {
   //한자리를 두자리로 변환
   public static String isTwo(String str) {
      return str.length()<2?"0"+str:str;   //5 --> "05"  10 --> "10"
   }
   //mdate날자 형식 변환 --> yy-MM-dd HH:mm
   public String toDates(String mdate) {
      //Date 형식: "yyyy-MM-dd HH:mm:ss"
      String m=mdate.substring(0,4)+"-"
            +mdate.substring(4,6)+"-"
            +mdate.substring(6,8)+" "
            +mdate.substring(8,10)+":"
            +"00:00";
      
      
      SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd HH:mm");
      Timestamp tm=Timestamp.valueOf(m); //문자열값을 Date타입으로 변환하는 코드
      return sdf.format(tm); //문자열 타입일 경우 date타입으로 변환해서 사용해야한다.
   }
   
   //요일별 날짜 색깔 적용하기: 파라미터 - i , dayOfWeek 필요
   //(공백수_현재일)%7==0 토요일
   //(공백수_현재일)%7==1 일요일
   public static String fontColor(int i, int dayOfWeek) {
      String str="black"; //평일
      if((dayOfWeek-1+i)%7==0) {//토요일
         str="blue";
      }else if((dayOfWeek-1+i)%7==1){ //일요일
         str="red";
      }
   return str;
   }
//   //일일별 일정 목록 구하는 기능
   public static String Cash(int i, List<CashDto> clist) {
      String d=isTwo(i+""); //1 --> "01" 2자리로 변환
      String calList=""; //"<p>title</p><p>title</p><p>title</p>"
      for (int j = 0; j < clist.size(); j++) {
         //한달 일정 목록중에 해당일(i)값과 일치하는지 여부 판단
         if(clist.get(j).getMdate().substring(8).equals(d)) {
            calList+="<p style='color:"+
                  (("수입".equals(clist.get(j).getMio()))?"blue":"red")
                  +"'>"
                        +(+clist.get(j).getMio().length()>7?
                              clist.get(j).getMio().substring(0,7)+"..":
                                 clist.get(j).getMio()+" : "+clist.get(j).getMoney()+"원")
                        +"</p>";

         }
      }
      return calList;
   }   
//   public static String Color(String mio, List<CashDto> calList) {
//      if((calList.getClass(CashDto.mio).value.equals("수입"))) {//토요일
//         str="blue";
//      }else if((dayOfWeek-1+i)%7==1){ //일요일
//         str="red";
//      }
//   return str;
//   }
   

//   public static String Color(List<CashDto> clist) {
//
//      String str = "red";
//            
//      if("수입".equals(((CashDto) clist).getMio())) {
//         
//         str+="<p>"+"blue"+"</p>";
//      }
//   return str;
//   }

   public static String Account(int i, List<AccountDto> alist) {
      String d=isTwo(i+""); //1 --> "01" 2자리로 변환
      String calList=""; //"<p>title</p><p>title</p><p>title</p>"
      for (int j = 0; j < alist.size(); j++) {
         //한달 일정 목록중에 해당일(i)값과 일치하는지 여부 판단
         if(alist.get(j).getTran_date().substring(8).equals(d)) {
            calList+="<p style='color:"+
                  (("입금".equals(alist.get(j).getInout_type()))?"blue":"red")
                  +"'>"
                  +(+alist.get(j).getInout_type().length()>7?
                  alist.get(j).getInout_type().substring(0,7)+"..":
                  alist.get(j).getInout_type()+" : "+alist.get(j).getTran_amt()+"원")

                  +"</p>";
         }
      }
      return calList;
   }
   
   }
