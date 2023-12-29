<%@page import="com.hk.fintech.controller.CalController"%>
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

	.money{
   width:1020px;
   height: 30px;
   margin: 0 auto;
   text-align: center;
   font-size: 25px;
   font-weight:bold;
   color: #3C4048;

   }
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
                        +"  <button type='button' class='btn btn-primary' onclick='addAccount()'>계좌등록</button>"
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
                       +'      <button onclick="deleteAccount(\''+res_list[i].fintech_use_num+'\', this)" type="button" class="btn btn-outline-primary">계좌삭제</button>'
                       +'   </div>'
                       +'   <div class="balance_amt"></div>'
                       +'</div>   '
               )
            }
         } //success
      });
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
										 "<p>잔액:"+data.balance_amt+"</p>"
										 +"<p><button class='btn btn-outline-primary' onclick='transactionList(\""+fintech_use_num+"\",this)'>거래내역조회</button></p>"
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
   
   
   
   var ctx = document.getElementById('myChart');
   var myChart = new Chart(ctx, {
     type: 'bar',
     data: {
       labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
       datasets: [{
         label: '# of Votes',
         data: [12, 19, 3, 5, 2, 3],
         backgroundColor: [
           'rgba(255, 99, 132, 0.2)',
           'rgba(54, 162, 235, 0.2)',
           'rgba(255, 206, 86, 0.2)',
           'rgba(75, 192, 192, 0.2)',
           'rgba(153, 102, 255, 0.2)',
           'rgba(255, 159, 64, 0.2)'
         ],
         borderColor: [
           'rgba(255, 99, 132, 1)',
           'rgba(54, 162, 235, 1)',
           'rgba(255, 206, 86, 1)',
           'rgba(75, 192, 192, 1)',
           'rgba(153, 102, 255, 1)',
           'rgba(255, 159, 64, 1)'
         ],
         borderWidth: 1
       }]
     },
     options: {
       scales: {
         yAxes: [{
           ticks: {
             beginAtZero: true
           }
         }]
       }
     }
   });
   
   
    </script>
 
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    <!-- 부트스트랩 -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
    crossorigin="anonymous"></script>
  <!-- 차트 -->

    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="resources/assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/resources/css/styles.css" rel="stylesheet" />
    <style type="text/css">
  
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
    
<div class="a" style="background: #277BC0; height: 10px; "></div>
<div class="b" style="background: #4CB9E7; height: 5px; margin-left:20px;"></div>

<div class="container">
<!--   총 합      -->
<div class="money">
   
   	
   <span>합계</span>
   <a> <%=request.getParameter("total")%>원</a>
   	
</div>


<canvas id="myChart"></canvas>
</div>

  
  
      
  



</body>
</html>