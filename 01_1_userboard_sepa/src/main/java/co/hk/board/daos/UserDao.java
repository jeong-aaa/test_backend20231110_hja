package co.hk.board.daos;

import java.util.ArrayList;
import java.util.List;

import com.hk.board.dtos.UserDto;

public class UserDao {
	//1단계 :드라이버 로딩
	public UserDao() {
		//강제 객체 생성 :예외처리를 해야함
		try {
			Class.forName("org.mariadb.jdbc.Driver");//마리아디비 드라이버 경로
			System.out.println("1단계:드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("1단계:드라이버로딩 실패");
		}
	}
	//회원목록 조회기능
	public List<UserDto> getAllUser(){
		//List는 여러행의 정보(dto객체들)를 담기위한 객체
		List<UserDto> list=new ArrayList<>();
		
		
		
		return list;
	}
}
