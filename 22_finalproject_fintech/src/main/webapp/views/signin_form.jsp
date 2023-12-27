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
   
   <script type="text/javascript">

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

<form action="/user/login" method="post" style=" width:800px; margin: 0 auto; margin-top: 100px; ">

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
   
   
   <div style="text-align: center; padding: 15px; ">
      <input type="submit" style="width: 100px;"  value="SignIn" class="btn btn-primary" /> 
   </div>
      
   <p style="text-align: center;"><a href="/user/adduser">회원이 아니신가요?</a></p>
   

</form>



<!-- <!-- 제발 --> 
<!--    <!-- Content section--> 
<!--     <section class="py-5"> -->
<!--         <div class="container my-5"> -->
<!--             <div class="row justify-content-center"> -->
<!--                 <div class="col-lg-6"> -->
<!--                     <form action="/user/login" method="post"> -->
<!--                        <table class="table"> -->
                         
<!--                           <tr> -->
<!--                              <th>이메일</th> -->
<!--                              <td><input type="email" name="useremail" class="form-control"/></td> -->
<!--                           </tr> -->
<!--                           <tr> -->
<!--                              <th>비밀번호</th> -->
<!--                              <td><input type="password" name="userpassword" class="form-control"/></td> -->
<!--                           </tr> -->
<!--                           <tr> -->
<!--                              <td colspan="2"> -->
                                
<!--                                 <input type="submit" value="SignIn" class="btn btn-dark"/> -->
<!--                                 <a href="/user/adduser">회원이 아니신가요?</a> -->
<!--                              </td> -->
<!--                           </tr> -->
<!--                        </table> -->
<!--                     </form> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </section> -->
    
    
</body>
</html>

