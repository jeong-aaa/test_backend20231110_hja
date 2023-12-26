<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
<header class="header">
   <nav class="navbar navbar-expand-lg navbar-dark" style="font-size: 25px; font-family: Arial,  sans-serif; font-weight: bolder; width: auto; background:#00000;">
        <div class="container" style="margin-left: 30px; width: auto;">           
          <img src="/resources/img/SSM.png" width="45" height="40" class="d-inline-block align-top" alt=""/>
          <a class="nav-link" href="/banking/main" style="color: #3C4048; font-family: Arial,  sans-serif; font-weight: bolder; " >
          S.S.M
           </a>           
            <div class="collapse navbar-collapse" id="navbarSupportedContent" style="margin-left: 830px; ">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                   <li class="nav-item"><a class="nav-link active"  href="/banking/main" style="white-space: nowrap; color: #3C4048;" >${sessionScope.ldto.username}님</a></li>
                    <li class="nav-item"><a class="nav-link" href="/schedule/calendar" style="color: #3C4048;" >Calender</a></li>
                    <li class="nav-item"><a class="nav-link" href="#!" style="color: #3C4048;" onclick="myInfo()">MyPage</a></li>
                    <li class="nav-item"><a class="nav-link" href="/user/logout" style="color: #3C4048;">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>
</body>
</html>