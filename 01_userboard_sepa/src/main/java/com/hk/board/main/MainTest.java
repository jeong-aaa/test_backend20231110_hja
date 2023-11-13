package com.hk.board.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.sql.Timestamp;
import java.util.List;

import com.hk.board.daos.UserDao;
import com.hk.board.dtos.UserDto;

public class MainTest {

   public static void main(String[] args) {
      UserDao dao=new UserDao();//객체생성을 하면 드라이버 로딩 실행
      
      //회원목록조회기능
      List<UserDto> list = dao.getAllUser();
      System.out.println();
      System.out.println("==회원목록==");
      for (int i = 0; i < list.size(); i++) {
//         System.out.println(list.get(i));
         System.out.println(list.get(i).getUserID()+"|"
                         +list.get(i).getName()+"|"
                         +list.get(i).getmDate());
      }
      
      //회원정보 등록하기
      UserDto dto=new UserDto();
      dto.setUserID("KKH");
      dto.setName("김경호");
      dto.setBirthYear(1971);
      dto.setAddr("전남");
      dto.setMobile1("019");
      dto.setMobile2("33333333");
      dto.setHeight(177);
      
      //boolean isS=dao.insertUser(dto);
      
      //회원정보수정하기
      //생성자 오버로딩을 활용하여 값 초기화
      boolean isS2=dao.updateUser(new UserDto("LSG", "이승기", 1988, "부산", "011", "11111111", 184, null));
      
      //회원정보상세조회
      System.out.println("회원상세정보조회");
      UserDto dto2=dao.getUser("LSG");
      System.out.println(dto2);
   }
  	//회원삭제:delete문
  	public boolean deleteUser(String userId) {
  		int count=0;//쿼리실행 성공개수
  		
  		Connection conn=null;
  		PreparedStatement psmt=null;
  		
  		//DB연결을 위한 정보 정의
  		String url="jdbc:mariadb://localhost:3306/hkedu";
  		String user="root";
  		String password="manager";
  		
  		String sql="DELETE FROM usertbl WHERE userID=?";
  		try {
  			conn=DriverManager.getConnection(url, user, password);
  			System.out.println("2단계:DB연결성공");
  			
  			psmt=conn.prepareStatement(sql);
  			psmt.setString(1, userId);
  			System.out.println("3단계:쿼리준비성공");
  			
  			count=psmt.executeUpdate();
  			System.out.println("4단계:쿼리실행성공");
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}finally {
  			try {
  				if(psmt!=null) {
  					psmt.close();
  				}
  				if(conn!=null) {
  					conn.close();
  				}
  				System.out.println("6단계:DB닫기성공");
  			} catch (SQLException e) {
  				System.out.println("6단계:DB닫기실패");
  				e.printStackTrace();
  			}
  		}
  		return count>0?true:false;
  	}
  }






