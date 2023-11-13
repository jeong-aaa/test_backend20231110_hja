package com.hk.board.dtos;

//패키지가 다른 클래스르 사용할때 반드시 작성해야 함
import java.util.Date;

public class UserDto {
	//private??:클래스 내부에서만 접근 가능
	private String userID;
	private String name;
	private int birthYear;
	private String addr;
	private String mobile1;
	private String mobile2;
	private int height;
	private String mDate;
	
	public UserDto() {
		
	}
	//생성자 오버로딩 :클래스이 맴버필드를 선택적으로 초기화할 수 있다.

	public UserDto(String userID, String name, int birthYear, String addr, String mobile1, String mobile2, int height,
			String mDate) {
		super();
		this.userID = userID;
		this.name = name;
		this.birthYear = birthYear;
		this.addr = addr;
		this.mobile1 = mobile1;
		this.mobile2 = mobile2;
		this.height = height;
		this.mDate = mDate;
	}

	public UserDto(String userID, String name, int birthYear, String addr, String mobile1, String mobile2, int height) {
		super();
		this.userID = userID;
		this.name = name;
		this.birthYear = birthYear;
		this.addr = addr;
		this.mobile1 = mobile1;
		this.mobile2 = mobile2;
		this.height = height;
	}
	
}
