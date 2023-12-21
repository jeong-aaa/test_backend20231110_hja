package com.hk.fintech.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hk.fintech.dtos.UserDto;

@Mapper
public interface UserMapper {
	public int addUser(UserDto dto);
	public UserDto loginUser(UserDto dto);
	public int addCardToken(UserDto dto);

	public boolean delete(UserDto dto);

	public boolean delUser(String useremail);

}
//제발