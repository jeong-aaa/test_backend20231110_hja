package com.hk.user.daos;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.hk.ans.config.SqlMapConfig;
import com.hk.user.dtos.UserDto;

public class UserDao extends SqlMapConfig{
   
   private String namespace="com.hk.user.";
   
   //싱글톤 객체: 객체를 한번만 생성하자
      private static UserDao userDao; //생성된 객체를 저장
      public UserDao() {} //new LoginDao() x : 외부접근금지
      public static UserDao getUserDao() { //메서드를 통해 접근 가능
         if(userDao==null) {
            userDao=new UserDao(); //내부에서 객체 생성
         }
         return userDao;
      }
   //사용자 기능
   
   //1. 회원가입 기능(enabled: 'Y', role: 'USER', regDate: 현재날짜)
   //insert문 작성
   public boolean insertUser(UserDto dto) {
      int count=0;
      SqlSession sqlSession=null;
      
      try {
        sqlSession=getSqlSessionFactory().openSession(true);
         count=sqlSession.insert(namespace+"insertUser");
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         sqlSession.close();
      }
      return count>0?true:false;
   }
   
   //2. 아이디 중복 체크하기
   //    select문 실행
   public String idCheck(String id) {
     //UserDto dto=null;
      String resultId=null;
      int count=0;
      SqlSession sqlSession=null;
      
      try {
         sqlSession=getSqlSessionFactory().openSession(true);
         count=sqlSession.selectOne(namespace+"idCheck");
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
         sqlSession.close();
      }
      return resultId;
   }
   
   //3. 로그인 기능: ID와 password를 통해 회원정보 조회
   //로그인 기능 만들어보기
   //main 메서드에 지금 만든 메서드 실행해 보기
   public  UserDto getLogin(String id, String password) {
      UserDto dto=new UserDto();
      dto.setId(id);
      dto.setPassword(password);

      SqlSession sqlSession=null;

      try {
      sqlSession=getSqlSessionFactory().openSession(true);
      dto=sqlSession.selectOne(namespace+"getLogin",dto);
   } catch (Exception e) {
      e.printStackTrace();
   }finally {
      sqlSession.close();
   }
      return dto;
   }
   
   //나의 정보 조회
   public UserDto getUserInfo(String id) {
      UserDto dto=new UserDto();
      int count=0;
      SqlSession sqlSession=null;

      try {
      sqlSession=getSqlSessionFactory().openSession(true);
      count=sqlSession.update(namespace+"getUserInfo");
   } catch (Exception e) {
      e.printStackTrace();
   } finally {
      sqlSession.close();
   }
      return dto;
   }
   
   //나의 정보 수정하기: update문, 파라미터:UserDto
   public boolean updateUser(UserDto dto) {
      int count=0;
      SqlSession sqlSession=null;
      
      try {
      sqlSession=getSqlSessionFactory().openSession(true);
      count=sqlSession.update(namespace+"updateUser");
   } catch (Exception e) {
      e.printStackTrace();
   } finally {
      sqlSession.close();
   }
      return count>0?true:false;
   }
   
   //회원탈퇴하기: update문 작성, enabled='N'
   public boolean delUser(String id) {
      int count=0;
      SqlSession sqlSession=null;
      
      try {
      sqlSession=getSqlSessionFactory().openSession(true);
      count=sqlSession.update(namespace+"delUser");
   } catch (Exception e) {
      e.printStackTrace();
   } finally {
      sqlSession.close();
   }
      return count>0?true:false;
   }
   
   //회원목록 전체조회
   public List<UserDto> getAllUserList(){
     List<UserDto> list=new ArrayList<>();
      
     SqlSession sqlSession=null;
      
      try {
      sqlSession=getSqlSessionFactory().openSession(true);
      list=sqlSession.selectList(namespace+"UserAllList");
   } catch (Exception e) {
      e.printStackTrace();
   }finally {
      sqlSession.close();
   }
      return list;
   }
   
   //회원목록 전체 조회[사용중]
   public List<UserDto> getUserList(){
      List<UserDto> list=new ArrayList<>();
      SqlSession sqlSession=null;
      
      try {
      sqlSession=getSqlSessionFactory().openSession(true);
      list=sqlSession.selectList(namespace+"UserList");
   } catch (Exception e) {
      e.printStackTrace();
   }finally {
      sqlSession.close();
   }
      return list;
   }   
   
   //회원등급수정
   public boolean userUpdateRole(String id, String role) {
      int count=0;
      SqlSession sqlSession=null;
      
      try {
      sqlSession=getSqlSessionFactory().openSession(true);
      count=sqlSession.update(namespace+"userUpdateRole");
   } catch (Exception e) {
      e.printStackTrace();
   }finally {
      sqlSession.close();
   }
      return count>0?true:false;
   }
}






