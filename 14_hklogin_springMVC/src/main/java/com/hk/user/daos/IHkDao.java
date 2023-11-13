package com.hk.user.daos;

import java.util.List;

import com.hk.user.dtos.UserDto;

public interface IHkDao {
	//회원가입 기능
	public boolean insertUser(UserDto dto);
	
	//아이디 중복 체크하기
	public UserDto idCheck(UserDto dto);
	
	//로그인 기능
	public UserDto getLogin(UserDto dto);
	
	//나의 정보 조회
	public UserDto getUserInfo(String id);
	
	//나의 정보 수정하기
	public boolean updateUser(UserDto dto);
	
	//회원 탈퇴하기
	public boolean delUser(String id);
	
	//회원목록 전체조회
	public List<UserDto> getAllUserList();
	
	//회원목록 전체 조회[사용중]
	public List<UserDto> getUserList();
	
	//회원등급수정
	public boolean userUpdateRole(UserDto dto);
	
}
