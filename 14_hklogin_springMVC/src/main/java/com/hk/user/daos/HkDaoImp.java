package com.hk.user.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.user.dtos.UserDto;

@Repository
public class HkDaoImp implements IHkDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace="com.hk.user.";
	
	@Override
	public boolean insertUser(UserDto dto) {
		int count=sqlSession.insert(namespace+"insertUser", dto);
		return count>0?true:false;
	}

	@Override
	public UserDto idCheck(UserDto dto) {
		return sqlSession.selectOne(namespace+"idCheck", dto);
	}

	@Override
	public UserDto getLogin(UserDto dto) {
		return sqlSession.selectOne(namespace+"getLogin", dto);
	}

	@Override
	public UserDto getUserInfo(String id) {
		return sqlSession.selectOne(namespace+"getUserInfo", id);
	}

	@Override
	public boolean updateUser(UserDto dto) {
		int count=sqlSession.update(namespace+"updateUser", dto);
		return count>0?true:false;
	}

	@Override
	public boolean delUser(String id) {
		Map<String, String>map=new HashMap<>();
		map.put("id",id);
		int count=sqlSession.delete(namespace+"delUser",map);
		return count>0?true:false;
	}

	@Override
	public List<UserDto> getAllUserList() {
		return sqlSession.selectList(namespace+"getAllUserList");
	}

	@Override
	public List<UserDto> getUserList() {
		return sqlSession.selectList(namespace+"getUserList");
	}

	@Override
	public boolean userUpdateRole(UserDto dto) {
		return sqlSession.selectOne(namespace+"userUpdateRole",dto);
	}

}
