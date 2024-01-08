<%@page import="com.hk.fintech.apidto.UserMeAccountDto"%>
<%@page import="com.hk.fintech.apidto.UserMeDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<!-- <html lang="en" style="height: 100%"> -->

<head>
  <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>SSM</title>
    
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
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
                        +"  <button type='submit' class='btn btn-outline-secondary' onclick='delUserEX()' style='margin-right:200px; margin-top:2%;'>회원탈퇴</button>"
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
   
//    //월별 수입/지출 차트
//    function createChartData() {
//    new Chart(document.getElementById("line-chartO"), {
// 	    type: 'line',
// 	    data: {
// 	      labels: ['${fn:substring(blist[4].mdate,0,7)}','${fn:substring(blist[2].mdate,0,7)}','${fn:substring(blist[0].mdate,0,7)}'],
// 	      datasets: [{ 
// 	          data: ['${dlist[4].tran_amt}','${dlist[2].tran_amt}','${dlist[0].tran_amt}'],
// 	          label: "입금",
// 	          borderColor: "#3e95cd",
// 	          fill: false
// 	        }, { 
// 	          data: ['${dlist[5].tran_amt}','${dlist[3].tran_amt}','${dlist[1].tran_amt}'],
// 	          label: "출금",
// 	          borderColor: "#8e5ea2",
// 	          fill: false
// 	        }
// 	      ]
// 	    },
// 	    options: {
// 	        title: {
// 	            display: true,
// 	            text: '월별수입지출',
// 	            fontSize: 18 // 전체 제목의 텍스트 크기 조절
// 	        },
// 	        scales: {
// 	            xAxes: [{
// 	                ticks: {
// 	                    fontSize: 14, // x축 레이블 텍스트 크기 조절
// 	                }
// 	            }],
// 	            yAxes: [{
// 	                ticks: {
// 	                    fontSize: 14, // y축 레이블 텍스트 크기 조절
// 	                }
// 	            }]
// 	        }

// 	    }

// 	  });
   
// 	  new Chart(document.getElementById("line-chartT"), {
// 	       type: 'line',
// 	       data: {
// 	         labels: ['${fn:substring(blist[4].mdate,0,7)}','${fn:substring(blist[2].mdate,0,7)}','${fn:substring(blist[0].mdate,0,7)}'],
// 	         datasets: [{ 
// 	             data: ['${blist[4].money}','${blist[2].money}','${blist[0].money}'],
// 	             label: "현금 수입",
// 	             borderColor: "#3e95cd",
// 	             fill: false
// 	           }, { 
// 	             data: ['${blist[5].money}','${blist[3].money}','${blist[1].money}'],
// 	             label: "현금 지출",
// 	             borderColor: "#8e5ea2",
// 	             fill: false
// 	           }
// 	         ]
// 	       },
// 	       options: {
// 	           title: {
// 	               display: true,
	              
// 	           },
// 	           scales: {
// 	               xAxes: [{
// 	                   ticks: {
// 	                       fontSize: 14, // x축 레이블 텍스트 크기 조절
// 	                   }
// 	               }],
// 	               yAxes: [{
// 	                   ticks: {
// 	                       fontSize: 14, // y축 레이블 텍스트 크기 조절
// 	                   }
// 	               }]
// 	           }

// 	       }

// 	     });
	  
// 	$("#come").show();
      
//    }

	function createChartData() {
		$("#plz").show();
		$("#come").hide();
	}

   function createChartWithData() {
//       new Chart(document.getElementById("line-chartS"), {
//             type: 'line',
//             data: {
//               labels: ['${fn:substring(blist[4].mdate,0,7)}','${fn:substring(blist[2].mdate,0,7)}','${fn:substring(blist[0].mdate,0,7)}'],
//               datasets: [
//                  { 
//                   data: [${blist[4].money}+${dlist[4].tran_amt},${blist[2].money}+${dlist[2].tran_amt},${blist[0].money}+${dlist[0].tran_amt}],
//                   label: "수입",
//                   borderColor: "#3e95cd",
//                   fill: false
//                 }, 
//                 { 
//                   data: [${blist[5].money}+${dlist[5].tran_amt},${blist[3].money}+${dlist[3].tran_amt},${blist[1].money}+${dlist[1].tran_amt}],
//                   label: "지출",
//                   borderColor: "#8e5ea2",
//                   fill: false
//                 }
//               ]
//             },
//             options: {
//                 title: {
//                     display: true,
                   
//                 },
//                 scales: {
//                     xAxes: [{
//                         ticks: {
//                             fontSize: 14, // x축 레이블 텍스트 크기 조절
//                         }
//                     }],
//                     yAxes: [{
//                         ticks: {
//                             fontSize: 14, // y축 레이블 텍스트 크기 조절
//                         }
//                     }]
//                 }

//             }

//           });
      
      $("#come").show();
      $("#plz").hide();
      
   }
   
   
    </script>
    
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <!-- 차트 링크 -->
  <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
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
    
<div class="a" style="background: #277BC0; height: 10px;"></div>
<div class="b" style="background: #4CB9E7; height: 5px; margin-left:20px;"></div>
  


<section class="py-4">
<div class="container">
   <div class="py-4">
      <div class="col" style="text-align: center;">
         <a id="chartButton" type="button"  style="color: #3C4048;" onclick="createChartWithData()">합계</a>
         <a id="chartBtn" type="button"  style="color: #3C4048;" onclick="createChartData()">월별수입/지출</a>
<!--               <h4>월별수입지출</h4> -->
      </div>
   </div>
<!--    <div class="row my-2" > -->
<!--       <div class="col"> -->
<!--          <div class="card"> -->
<!--             <div class="card-body"> -->
<!--                <canvas id="line-chartO" height="100"></canvas> -->
<!--                <canvas id="line-chartT" height="100"></canvas> -->
<!--                     <canvas id="line-chartS" height="100"></canvas> -->
<!--             </div> -->
<!--          </div> -->
<!--       </div> -->
<!--    </div> -->
   <div class="row my-2" id="plz">
      <div class="col">
         <div class="card">
            <div class="card-body">
               <canvas id="line-chartO" height="100"></canvas>
               <canvas id="line-chartT" height="100"></canvas>
<!--                     <canvas id="line-chartS" height="100"></canvas> -->
            </div>
         </div>
      </div>
   </div>
   <div class="row my-2" id="come">
      <div class="col">
<!--          <div class="card"> -->
<!--             <div class="card-body"> -->
<!-- 	               <canvas id="line-chartO" height="100"></canvas> -->
<!-- 	               <canvas id="line-chartT" height="100"></canvas> -->
                   <canvas id="line-chartS" height="100"></canvas>
            </div>
         </div>
      </div>
<!--    </div> -->
<!-- </div> -->
</section>



  <!-- 부트스트랩 -->
  <script src="https://code.jquery.com/jquery-3.3.1.min.js"
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
    crossorigin="anonymous"></script>
  <!-- 차트 -->
 
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

<script>
  new Chart(document.getElementById("line-chartO"), {
    type: 'line',
    data: {
      labels: ['${fn:substring(blist[4].mdate,0,7)}','${fn:substring(blist[2].mdate,0,7)}','${fn:substring(blist[0].mdate,0,7)}'],
      datasets: [{ 
          data: ['${dlist[4].tran_amt}','${dlist[2].tran_amt}','${dlist[0].tran_amt}'],
          label: "입금",
          borderColor: "#3e95cd",
          fill: false
        }, { 
          data: ['${dlist[5].tran_amt}','${dlist[3].tran_amt}','${dlist[1].tran_amt}'],
          label: "출금",
          borderColor: "#8e5ea2",
          fill: false
        }
      ]
    },
    options: {
        title: {
            display: true,
            text: '월별수입지출',
            fontSize: 18 // 전체 제목의 텍스트 크기 조절
        },
        scales: {
            xAxes: [{
                ticks: {
                    fontSize: 14, // x축 레이블 텍스트 크기 조절
                }
            }],
            yAxes: [{
                ticks: {
                    fontSize: 14, // y축 레이블 텍스트 크기 조절
                }
            }]
        }

    }

  });
  
  new Chart(document.getElementById("line-chartT"), {
       type: 'line',
       data: {
         labels: ['${fn:substring(blist[4].mdate,0,7)}','${fn:substring(blist[2].mdate,0,7)}','${fn:substring(blist[0].mdate,0,7)}'],
         datasets: [{ 
             data: ['${blist[4].money}','${blist[2].money}','${blist[0].money}'],
             label: "현금 수입",
             borderColor: "#3e95cd",
             fill: false
           }, { 
             data: ['${blist[5].money}','${blist[3].money}','${blist[1].money}'],
             label: "현금 지출",
             borderColor: "#8e5ea2",
             fill: false
           }
         ]
       },
       options: {
           title: {
               display: true,
              
           },
           scales: {
               xAxes: [{
                   ticks: {
                       fontSize: 14, // x축 레이블 텍스트 크기 조절
                   }
               }],
               yAxes: [{
                   ticks: {
                       fontSize: 14, // y축 레이블 텍스트 크기 조절
                   }
               }]
           }

       }

     });
  
  new Chart(document.getElementById("line-chartS"), {
      type: 'line',
      data: {
        labels: ['${fn:substring(blist[4].mdate,0,7)}','${fn:substring(blist[2].mdate,0,7)}','${fn:substring(blist[0].mdate,0,7)}'],
        datasets: [
           { 
            data: [${blist[4].money}+${dlist[4].tran_amt},${blist[2].money}+${dlist[2].tran_amt},${blist[0].money}+${dlist[0].tran_amt}],
            label: "수입",
            borderColor: "#3e95cd",
            fill: false
          }, 
          { 
            data: [${blist[5].money}+${dlist[5].tran_amt},${blist[3].money}+${dlist[3].tran_amt},${blist[1].money}+${dlist[1].tran_amt}],
            label: "지출",
            borderColor: "#8e5ea2",
            fill: false
          }
        ]
      },
      options: {
          title: {
              display: true,
             
          },
          scales: {
              xAxes: [{
                  ticks: {
                      fontSize: 14, // x축 레이블 텍스트 크기 조절
                  }
              }],
              yAxes: [{
                  ticks: {
                      fontSize: 14, // y축 레이블 텍스트 크기 조절
                  }
              }]
          }

      }

    });
  
  
  
</script>
  
</body>

</html>