package com.hk.fintech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.fintech.dtos.UserDto;
import com.hk.fintech.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public boolean addUser(UserDto dto) {
		int count=userMapper.addUser(dto);
		return count>0?true:false;
	}
	
	public UserDto loginUser(UserDto dto) {
		return userMapper.loginUser(dto);
	}
	
	public int addCardToken(UserDto dto) {
		return userMapper.addCardToken(dto);
	}

	@Transactional
	public boolean delUser(String useremail) {
		try {
            //  회원 탈퇴를 위해 UserMapper의 delUser 메서드 호출
            userMapper.delUser(useremail);
            return true ;
            
        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            System.out.println("회원 탈퇴 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
	}

}
//제발