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

   <!-- Content section-->
    <section class="py-5">
        <div class="container my-5">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <form action="/user/login" method="post">
                       <table class="table">
                         
                          <tr>
                             <th>이메일</th>
                             <td><input type="email" name="useremail" class="form-control"/></td>
                          </tr>
                          <tr>
                             <th>비밀번호</th>
                             <td><input type="password" name="userpassword" class="form-control"/></td>
                          </tr>
                         
                          <tr>
                             <td colspan="2">
                                
                                <input type="submit" value="signIn" class="btn btn-dark"/>
                             </td>
                          </tr>
                       </table>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
    
</body>
</html>


