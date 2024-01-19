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
   
   /********************************/

/*   이미지 슬라이드 쇼 영역    */

/********************************/

​.Slidesbackground {
    margin: 0;
    padding: 0;

}
.slideshow-image{
    border-radius:3%;
    width: 100%;
    height: 100%;
    overflow: hidden;
}
/* .mySlides { */
/*     border-radius:3%; */
/*     width: 600px; */
/*     height: 350px; */
/*     display: flex; */
/*     justify-content: center; */
/*     align-items: center; */
/*     box-shadow: 0px 15px 15px rgba(0, 0, 0, 0.5); */
/* } */

.mySlides {
/*     border-radius: 3%; */
    width: 100%; /* 수정: 슬라이드가 부모 요소에 꽉 차도록 수정 */
    height: 350px;
/*     display: flex; */
    justify-content: center;
    align-items: center;
/*      box-shadow: 0px 15px 15px rgba(0, 0, 0, 0.5);  */
/*     display: none; /* 수정: 일단 모든 슬라이드를 숨김 */ */
}

.mySlides.b{
 	display: none; 
}
.mySlides.c{
 	display: none; 
}

.slideshow-container {
    display: flex;
    justify-content: center;
    position: relative;
    margin: auto;
}

.fade {
    animation-name: fade;
    animation-duration: 1.5s;
}

@keyframes fade {
    from {
        opacity: .4
    }
    to {
        opacity: 1
    }
}
</style>
<script type="text/javascript">

var slideIndex = 0;
showSlides(); // 페이지 로드 시에 슬라이드 쇼 함수 호출

function showSlides() {
    var i;
    var slides = document.getElementsByClassName("mySlides");

    // 모든 슬라이드를 숨김
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }

    slideIndex++;
    if (slideIndex > slides.length) {
        slideIndex = 1;
    }

    // 현재 slideIndex에 해당하는 슬라이드만 보여줌
    if (slides[slideIndex - 1]) {
        slides[slideIndex - 1].style.display = "block";
    }

    setTimeout(showSlides, 2000); // Change the delay to 2000 milliseconds (2 seconds)
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
    
<div class="a" style="background: #277BC0; height: 10px; ">
   <section class="py-4">
      <div class="c" style="background: #00000; height: 250px; text-align : center;">
<!--       	<img src="/resources/img/MAIN01.jpg" alt="" style="height: 240px; width: 900px;"/> -->
              	<span class="slideshow-container">
        		 <div class="Slidesbackground">
					<div class="mySlides a">
					    <img src="/resources/img/front-view-arrangement-of-economy-elements.jpg" style="height: 240px; width: 900px;" alt="Slide 1">
					</div>
					<div class="mySlides b">
					    <img src="/resources/img/MAIN01.jpg" style="height: 240px; width: 900px;" alt="Slide 2">
					</div>
					<div class="mySlides c">
					    <img src="/resources/img/stacked-coins-math-blocks-calculator-and-piggybank-on-wooden-tabletop.jpg" style="height: 240px; width: 900px;" alt="Slide 3">
					</div>
        		 </div>
        	 </span> 
      </div>
      
      <div class="d" style="background: #f4f7fc; height: 90px; ">
         <div class="main_service_menu">
            <h2 class="blind" style="margin-left:630px; font-size: 25px; clolr:#163020;">주요 서비스 바로가기</h2>
            <div class="dropdown">
                 <button class="dropbtn" style="margin-left:500px; ">계정</button>
                 <div class="dropdown-content" style="margin-left:500px;">
                      <a href="/user/signin_form">로그인</a>
                      <a href="/user/signup">회원가입</a>
                 </div>
            </div>
            <div class="dropdown">
                 <button class="dropbtn" style="margin-left:100px; ">계좌</button>
                 <div class="dropdown-content" style="margin-left:100px; ">
                      <a href="/user/signin_form">계좌추가</a>
                      <a href="/user/signin_form">계좌조회</a>
                 </div>
            </div>
            <div class="dropdown">
                 <button class="dropbtn" style="margin-left:100px; ">가계부</button>
                 <div class="dropdown-content" style="margin-left:100px; ">
                      <a href="/user/signin_form">수입/지출</a>
                      <a href="/user/signin_form">거래상세내역</a>
                      <a href="/user/signin_form">현금거래내역</a>
                      <a href="/user/signin_form">월별지출그래프</a>
                 </div>
            </div>
            <div class="dropdown">
                 <button class="dropbtn" style="margin-left:100px; ">마이페이지</button>
                 <div class="dropdown-content" style="margin-left:100px; ">
                      <a href="/user/signin_form">계좌관리</a>
<!--                       <a href="#!">회원탈퇴</a> -->
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
         </section>
      <div>
         <footer class="footer">
                <div class="footer" style="background:#277BC0; height:50px;"><p class="m-0 text-white" style="text-align : center; align-items : center;">Copyright &copy; S.S.M Website 2023</p></div>
         </footer> 
      </div>
   </div>
</div>
<div class="b" style="background: #4CB9E7; height: 5px; margin-left:20px;">
</div>
</body>
</html>