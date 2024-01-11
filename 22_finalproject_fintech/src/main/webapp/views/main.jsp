<%@page import="com.hk.fintech.apidto.UserMeAccountDto"%>
<%@page import="com.hk.fintech.apidto.UserMeDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>SSM</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <style type="text/css">
   .box{border-bottom: 1px solid gray; margin-bottom: 10px;}
   .box > .sub_menu{text-align: right;}
   .addAccount{text-align: right;}
   </style>
   <script type="text/javascript">
   
   //나의 정보조회[계좌목록]
   function myInfo(){
      $.ajax({
//            url:"https://testapi.openbanking.or.kr/v2.0/user/me",
//            headers:{"Authorization":"Bearer token"},
//            data:{"user_seq_no":"사용자번호"}
         url:"/banking/myinfo",
         method:"get",
         dataType:"json",
         success:function(data){
            console.log(data.res_list);
            var res_list=data.res_list;//나의 계좌목록 저장
            
            
            //계좌등록 버튼
            $("#list").html("<div class='addAccount'>"
                        +"  <button type='submit' class='btn btn-outline-secondary' onclick='delUserEX()' style='margin-right:110px'>회원탈퇴</button>"
                        +"</div>"
                          );
            
            //출력할 내용
            //계좌이름
            //핀테크이용번호 [은행이름]
            for (var i = 0; i < res_list.length; i++) {
               $("#list").append(
                  '<div class="box container">'
                       +'   <div>'
                       +'      <h1>'+res_list[i].account_alias+'</h1>'
                       +'      <p>'+res_list[i].fintech_use_num+' ['+res_list[i].bank_name+']</p>'
                       +'   </div>'
                       +'   <div class="sub_menu"> '
                       +'      <button onclick="balance(\''+res_list[i].fintech_use_num+'\',this)" type="button" class="btn btn-outline-primary">잔액조회</button>'
                       +'   </div>'
                       +'   <div class="balance_amt"></div>'
                       +'</div>   '
               )
            }
         } //success
      });
   }
   
   //계좌해지...해보는중
   function deleteAccount(fintechUseNum, btnEle) {
    if (confirm("정말로 계좌를 삭제하시겠습니까?")) {
        $.ajax({
            url: "/banking/deleteAccount",
            method: "get",
            data: {"fintech_use_num": fintechUseNum},
            dataType: "json",
            success: function (data) {
                if (data.result === "success") {
                    // 삭제 성공 시 해당 계좌의 UI를 제거
                    $(btnEle).parents(".box").eq(0).remove();
                    alert("계좌가 성공적으로 삭제되었습니다.");
                } else {
                    alert("계좌 삭제에 실패했습니다. 에러: " + data.error);
                }
            },
            error: function () {
                alert("통신 실패");
            }
        });
    }
}
   
   //잔액조회하기
   function balance(fintech_use_num,btnEle){
      $.ajax({
         url:"/banking/balance",
         method:"get",
         data:{"fintech_use_num":fintech_use_num},
         dataType:"json",
         success:function(data){
            console.log(data);
            var box=$(btnEle).parents(".box").eq(0);
            box.find(".balance_amt").html(
                               "<p>잔액: "+data.balance_amt+"원"+"</p>"
                               +"<p><button type='button' class='btn btn-primary' onclick='transactionList(\""+fintech_use_num+"\",this)'>거래내역조회</button></p>"
                              +"<div class='transaction_list'></div>"  //거래내역이 출력될 div 
                              );          
         },
         error:function(){
            alert("통신실패");
         }
      });
   }
   
   //거래내역조회
   function transactionList(fintech_use_num,btnEle){
      $.ajax({
         url:"/banking/transactionList",
         method:"get",
         data:{"fintech_use_num":fintech_use_num},
         dataType:"json",
         success:function(data){ //data: 응답결과을 받을 변수
            console.log(data.res_list);
            var list="<ul>";
            // data.res_list  -->  배열
            for (var i = 0; i < data.res_list.length; i++) {
               var res=data.res_list[i];// json객체를 가져온다 {key:value,...}
               list+="<li>"+res.tran_date+" "
                           +res.inout_type+" "
                           +res.print_content+":"
                           +res.tran_amt+"원"+"</li>"
            }
            list+="</ul>";// <ul><li>거래내역1</li><li>거래내역2</li>..</ul>
            //button .   p    . <div> 
            $(btnEle).parent().next(".transaction_list").html(list);
         }
      });
   }
   
   
   //계좌등록하기(센터인증 이용기관용: 사용자 인증후에 계좌 등록 가능)
   function addAccount(){
      var url="https://testapi.openbanking.or.kr/oauth/2.0/authorize?"
            +"response_type=code&" //고정값 code: 인증요청시 반환되는 값의 형식의미
            +"client_id=4987e938-f84b-4e23-b0a2-3b15b00f4ffd&" //이용기관의 ID
            +"redirect_uri=http://localhost:8087/banking/addaccount&"//응답URL
            +"scope=login inquiry transfer&" //토큰의 권한
            +"state=12345678123456781234567812345678&" //32자리 난수 설정
            +"auth_type=0"; //0:최초 한번 인증, 2:인증생략
            
      window.open(url,"인증하기","width=400px,height=600px");      
   }
   
   //회원 탈퇴하기
   function delUserEX(){
      if(confirm("정말로 탈퇴하시겠습니까??")){
         location.href = "/user/delUser";
         alert("회원탈퇴되었습니다.");
      }
   }
   
   
    </script>
    
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="resources/assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/resources/css/styles.css" rel="stylesheet" />
    <style type="text/css">
   /* Style The Dropdown Button */
   .dropbtn {
     background-color: #277BC0;
     color: white;
     padding: 10px;
     font-size: 16px;
     border: none;
     cursor: pointer;
   }
   
   /* The container <div> - needed to position the dropdown content */
   .dropdown {
     position: relative;
     display: inline-block;
   }
   
   /* Dropdown Content (Hidden by Default) */
   .dropdown-content {
     display: none;
     position: absolute;
     background-color: #f9f9f9;
     min-width: 160px;
     box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
     z-index: 1;
     
   }
   
   /* Links inside the dropdown */
   .dropdown-content a {
     color: black;
     padding: 12px 16px;
     text-decoration: none;
     display: block;
   }
   
   /* Change color of dropdown links on hover */
   .dropdown-content a:hover {background-color: #EAECCC}
   
   /* Show the dropdown menu on hover */
   .dropdown:hover .dropdown-content {
     display: block;
   }
   
   /* Change the background color of the dropdown button when the dropdown content is shown */
   .dropdown:hover .dropbtn {
     background-color: #EAECCC;
   }
   
   </style>
</head>
<body>
   <nav class="navbar navbar-expand-lg navbar-dark" style="font-size: 25px; font-family: Arial,  sans-serif; font-weight: bolder; width: auto; background:#00000;">
        <div class="container" style="margin-left: 30px; width: auto;">           
          <img src="/resources/img/SSM.png" width="45" height="40" class="d-inline-block align-top" alt=""/>
          <a class="nav-link" href="/banking/main" style="color: #3C4048; font-family: Arial,  sans-serif; font-weight: bolder; " >
          S.S.M
           </a>           
            <div class="collapse navbar-collapse" id="navbarSupportedContent" style="margin-left: 850px; ">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                   <li class="nav-item"><a class="nav-link active"  href="/banking/main" style="white-space: nowrap; color: #3C4048;" >${sessionScope.ldto.username}님</a></li>
<!--                     <li class="nav-item"><a class="nav-link " aria-current="page" href="/">Main</a></li> -->
                    <li class="nav-item"><a class="nav-link" href="/schedule/calendar" style="color: #3C4048;">Calender</a></li>
                    <li class="nav-item"><a class="nav-link" href="#!" style="color: #3C4048;" onclick="myInfo()">MyPage</a></li>
                    <li class="nav-item"><a class="nav-link" href="/user/logout" style="color: #3C4048;">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
    
<div class="a" style="background: #277BC0; height: 10px; ">
    <section class="py-4">
      <div id="list">
         <div class="c" style="background: #00000; height: 250px; text-align : center;" >
            <img src="/resources/img/MAIN01.jpg" alt="" style="height: 240px; width: 900px;"/>
         </div>
         
         <div class="d" style="background: #f4f7fc; height: 90px; ">
            <div class="main_service_menu">
               <h2 class="blind" style="margin-left:630px; font-size: 25px; clolr:#163020; ">주요 서비스 바로가기</h2>
               <div class="dropdown">
                    <button class="dropbtn" style="margin-left:500px; ">계정</button>
                    <div class="dropdown-content" style="margin-left:500px;">
                         <a href="/user/logout">로그아웃</a>
                    </div>
               </div>
               <div class="dropdown">
                    <button class="dropbtn" style="margin-left:100px; ">계좌</button>
                    <div class="dropdown-content" style="margin-left:100px; ">
                         <a href="#" onclick="myInfo()">계좌조회</a>
                    </div>
               </div>
               <div class="dropdown">
                    <button class="dropbtn" style="margin-left:100px;">가계부</button>
                    <div class="dropdown-content" style="margin-left:100px; ">
                         <a href="/schedule/calendar">수입/지출</a>
                         <a href="/schedule/calendar">거래상세내역</a>
                         <a href="/schedule/calendar">현금거래내역</a>
                         <a href="/banking/chart">월별지출그래프</a>
                    </div>
               </div>
               <div class="dropdown">
                    <button class="dropbtn" style="margin-left:100px;" onclick="myInfo()">마이페이지</button>
                    <div class="dropdown-content" style="margin-left:100px; ">
                         <a href="#" onclick="myInfo()">계좌관리</a>
<!--                          <a href="/user/delUser">회원탈퇴</a> -->
                    </div> 
               </div>
         
            </div>
         </div>
         
         <div class="e" style="background: #00000;" >
         <h5  style="color: #163020; margin-left:130px; margin-right:130px;">
         <br/>
         <p>재무 건강 관리는 매우 중요합니다. 우리의 삶은 돈과 밀접하게 연관되어 있기 때문에 재정 거래를 효과적으로 관리하는 것은 생활의 질을 향상시키고 안정성을 높이는 데 큰 도움이 됩니다.</p><br/>
         
         <p>S.S.M은 물건 구매부터 월급까지 모든 재정 거래를 손쉽게 관리할 수 있도록 도와줍니다. 간편한 가계부 기능을 통해 일일 지출과 수입을 체계적으로 기록할 수 있습니다. 씀은 언제 어디서든 재무 상태를 파악하고 관리할 수 있도록 도와줍니다.<br/>
         
         뿐만 아니라, S.S.M은 여러 은행이나 금융 기관의 계좌를 한 곳에서 효과적으로 관리할 수 있는 기능을 제공합니다. 여러 은행 계좌를 간편하게 모니터링하고, 자산을 효율적으로 이동시키며, 예산을 관리하며, 투자 및 저축 목표를 설정할 수 있습니다.</p><br/>
         
         <p>S.S.M은 신속한 기능과 직관적인 인터페이스를 통해 사용자가 금융을 더 효율적으로 관리할 수 있도록 도와줍니다. 걱정 없는 재무 거래를 위한 완벽한 도구로 여러분의 편의를 위해 설계되었습니다. 여러분의 재무 건강을 향상시키고 안정성을 높이는 데 도움을 드리겠습니다.</p></h5>
         <br/>
         </div> 
         </div>
            <div id="feignList">
               <%
                  UserMeDto dto=(UserMeDto)request.getAttribute("userMeDto");
                  if(dto!=null){
                     List<UserMeAccountDto>list=dto.getRes_list();
                     
                     for(UserMeAccountDto udto:list){
                        %>
                        <p><%=udto.getAccount_alias() %><br>
                           <%=udto.getFintech_use_num() %>
                           [<%=udto.getBank_name() %>]
                        </p>
                        <%
                     }//for
                  }//if
               %>
            </div>
   </section>
   <div>
       <footer class="footer">
              <div class="footer" style="background:#277BC0; height:50px;"><p class="m-0 text-white" style="text-align : center; align-items : center;">Copyright &copy; S.S.M Website 2023</p></div>
       </footer> 
   </div>
</div>    

<div class="b" style="background: #4CB9E7; height: 5px; margin-left:20px;">
</div> 

</body>
</html>