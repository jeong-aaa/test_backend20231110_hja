package com.hk.user.service;

import java.util.List;

import com.hk.user.daos.IHkDao;
import com.hk.user.dtos.RoleStatus;
import com.hk.user.dtos.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HkServiceImp implements IHkService {
	
	@Autowired
	private IHkDao hkDao;
	
	@Override
	public boolean insertUser(UserDto dto) {
		dto.setRole(String.valueOf(RoleStatus.USER));
		return hkDao.insertUser(dto);
	}

	@Override
	public UserDto idCheck(UserDto dto) {
		return hkDao.idCheck(dto);
	}

	@Override
	public UserDto getLogin(UserDto dto) {
		return hkDao.getLogin(dto);
	}

	@Override
	public UserDto getUserInfo(String id) {
		return hkDao.getUserInfo(id);
	}

	@Override
	public boolean updateUser(UserDto dto) {
		return hkDao.updateUser(dto);
	}

	@Override
	public boolean delUser(String id) {
		return hkDao.delUser(id);
	}

	@Override
	public List<UserDto> getAllUserList() {
		return hkDao.getAllUserList();
	}

	@Override
	public List<UserDto> getUserList() {
		return hkDao.getUserList();
	}

	@Override
	public boolean userUpdateRole(UserDto dto) {
		return hkDao.userUpdateRole(dto);
	}


}
