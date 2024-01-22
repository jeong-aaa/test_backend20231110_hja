<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8" />
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
   <meta name="description" content="" />
   <meta name="author" content="" />
   <title>SSM</title>
   <!-- Favicon-->
   <link rel="icon" type="image/x-icon" href="resources/assets/favicon.ico" />
   <!-- Core theme CSS (includes Bootstrap)-->
   <link href="/resources/css/styles.css" rel="stylesheet" />
   <!-- 제발 -->
   <script type="text/javascript">
      //사용자 인증 요청하기(사용자 인증하면서 계좌등록까지 진행함)
      function authorization(){
         var url="https://testapi.openbanking.or.kr/oauth/2.0/authorize?"
               +"response_type=code&" //고정갑 code:인증요청시 반환되는 값의 형식 의미
               +"client_id=4987e938-f84b-4e23-b0a2-3b15b00f4ffd&" //이용기관의 ID
               +"redirect_uri=http://localhost:8087/user/authresult&" //응답URL
               +"scope=login inquiry transfer&" //토큰의 권한
               +"state=12345678123456781234567812345678&" //32자리 난수 설정
               +"auth_type=0"; //0: 최초 한번 인증, 2: 인증생략
               
         window.open(url,"인증하기","width=400px, height=600px");
      }
      
      
      
      function Chk(form){
          //입력된 ID값 구하기
          var id=document.getElementsByName("username")[0].value;
          var email = document.getElementsByName("useremail")[0].value;
          var password = document.getElementsByName("userpassword")[0].value;
          
          if(id==""){
             alert("이름을 입력하세요");
             return false;
          }
          else if (email=="") {
        	  alert("이메일을 입력하세요");
        	  return false;
		}
          else if (password=="") {
        	  alert("비밀번호를 입력하세요");
        	  return false;
		}
          

       }
      
   </script>   

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark" style="font-size: 25px; font-family: Arial,  sans-serif; font-weight: bolder; width: auto; background:#00000;">
	<div class="container" style="margin-left: 30px; width: auto;">           
	  <img src="/resources/img/SSM.png" width="45" height="40" class="d-inline-block align-top" alt=""/>
	  <a class="nav-link" href="/banking/main" style="color: #3C4048; font-family: Arial,  sans-serif; font-weight: bolder; " >
	  S.S.M
	   </a>           
	    <div class="collapse navbar-collapse" id="navbarSupportedContent" style="margin-left: 1000px; ">
	        <ul class="navbar-nav ms-auto mb-2 mb-lg-0" >
	            <li class="nav-item"><a class="nav-link active" aria-current="page" href="/" style="color: #3C4048;">Main</a></li>
	            <li class="nav-item"><a class="nav-link" href="/user/signin_form" style="color: #3C4048;">SignIn</a></li>
	            <li class="nav-item"><a class="nav-link" href="/user/signup" style="color: #3C4048;">SignUp</a></li> 
	        </ul>
	    </div>
	</div>
</nav>

<div class="a" style="background: #277BC0; height: 10px; "></div>
<div class="b" style="background: #4CB9E7; height: 5px; margin-left:20px;"></div>

<form action="/user/adduser" method="post"  onsubmit = "return Chk(this)" style=" width:800px; margin: 0 auto; margin-top: 80px; ">
<div style="text-align: center; color: blue; font-size: 40px; font-weight: bold; ">SIGNUP</div>
<div style="height: 40px;"></div>
	<div class="mb-3 row" style="width: 500px; margin: 0 auto;">
		<label for="staticEmail" class="col-sm-2 col-form-label" style="width: 110px; text-align: center; " >이름</label>
		<div class="col-sm-10" style="width: 270px;">
			<input type="text" class="form-control" name="username"  >
			
		</div>
	</div>
	
	<div class="mb-3 row" style="width: 500px; margin: 0 auto;">
		<label for="staticEmail" class="col-sm-2 col-form-label" style="width: 110px; text-align: center;" >이메일</label>
		<div class="col-sm-10" style="width: 270px;">
			<input type="email" class="form-control" name="useremail"  >
		</div>
	</div>
	
	<div class="mb-3 row" style="width: 500px; margin: 0 auto;">
		<label for="inputPassword" class="col-sm-2 col-form-label" style="width: 110px; text-align: center;">비밀번호</label>
		<div class="col-sm-10" style="width: 270px;">
			<input type="password" class="form-control" name="userpassword">
		</div>
	</div>
	
	<div class="mb-3 row" style="width: 500px; margin: 0 auto;">
		<label for="inputPassword" class="col-sm-2 col-form-label" style="width: 110px; text-align: center;">AccessToken</label>
		<div class="col-sm-10" style="width: 270px;">
			<input type="text" class="form-control" name="useraccesstoken" >
		</div>
	</div>
	
	<div class="mb-3 row" style="width: 500px; margin: 0 auto;">
		<label for="inputPassword" class="col-sm-2 col-form-label" style="width: 110px; text-align: center;">RefreshToken</label>
		<div class="col-sm-10" style="width: 270px;">
			<input type="text" class="form-control" name="userrefreshtoken">
		</div>
	</div>
	
	<div class="mb-3 row" style="width: 500px; margin: 0 auto;">
		<label for="inputPassword" class="col-sm-2 col-form-label" style="width: 110px; text-align: center;">userSeqNo</label>
		<div class="col-sm-10" style="width: 270px;">
			<input type="text" class="form-control" name="userseqno" >
		</div>
	</div>
	<div style="text-align: center; padding: 20px;">
		<input type="button" value="인증하기" onclick="authorization()" class="btn btn-outline-danger" /> 
		<input type="submit" value="가입하기" class="btn btn-outline-primary" />
	</div>
	
	
	<p style="text-align: center;"><a href="/user/signin_form">계정이 있으신가요? 로그인하기</a></p>
		

</form>



   
</body>
</html>







