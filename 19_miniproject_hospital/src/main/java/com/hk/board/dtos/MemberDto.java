package com.hk.board.dtos;

import org.apache.ibatis.type.Alias;

@Alias(value="memberDto")
public class MemberDto {
   private int memberId;
   private String number;
   private String id;
   private String name;
   private String password;
   
   public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDto(int memberId, String number, String id, String name, String password) {
		super();
		this.memberId = memberId;
		this.number = number;
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "MemberDto [memberId=" + memberId + ", number=" + number + ", id=" + id + ", name=" + name
				+ ", password=" + password + "]";
	}
	  
   
   
   
}