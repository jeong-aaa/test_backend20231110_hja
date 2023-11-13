package com.hk.board.daos;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.ans.config.SqlMapConfig;
import com.hk.board.daos.HkDao;
import com.hk.board.dtos.HkDto;

public class HkDao extends SqlMapConfig {
	
	public String namespace="com.hk.board.";

	//1.글목록조회[페이징처리]
	   public List<HkDto>getAllList(String pnum){
	      List<HkDto>list=new ArrayList<>();
	      
	      SqlSession sqlSession=null;
	      
	      Map<String,String>map=new HashMap<>();
	      map.put("pnum", pnum);   //페이지 번호 저장
	      
	      try {
	         sqlSession=getSqlSessionFactory().openSession(true);
	         list=sqlSession.selectList(namespace+"boardList",map);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         sqlSession.close();
	      }
	      
	      return list;
	   }
	
	 //1-2.페이지 수 구하기
	   public int getPCount() {
	      int count=0;
	      SqlSession sqlSession=null;
	      
	      try {
	         sqlSession=getSqlSessionFactory().openSession(true);
	         count=sqlSession.selectOne(namespace+"getPCount");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         sqlSession.close();
	      }
	      return count;
	   }
	   
	   //2.새 글 추가하기
	   public boolean insertBoard(HkDto dto) {
	      int count=0;
	      
	      SqlSession sqlSession=null;
	      try {
	         sqlSession=getSqlSessionFactory().openSession(true);
	         //파라미터 타입: DTO, Array, Map(파라미터 기본타입)
	         //         값 한개(int,String해당타입으로 정의)
	         count=sqlSession.insert(namespace+"insertBoard", dto);
	      } catch (Exception e) {
	         e.printStackTrace();   //사용해줘야함 없으면 오류 출력 X
	      }finally {
	         sqlSession.close();
	      }
	      
	      return count>0?true:false;
	   }
	   
	   //3.상세조회
	   public HkDto getBoard(int seq) {
		   HkDto dto=null;
	      
	      //쿼리를 실행하려면 필요한 객체 sqlsession
	      SqlSession sqlSession=null;
	      
	      //Map에 담아서 파라미터 전달하기
	      Map<String,Integer>map=new HashMap<>();
	      map.put("seq", seq);
	      try {
	         sqlSession=getSqlSessionFactory().openSession(true);
//	         dto=sqlSession.selectOne(namespace+"getBoard",seq);
	         dto=sqlSession.selectOne(namespace+"getBoard",map);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         sqlSession.close();
	      }
	      return dto;
	   }
	   //4. 수정하기
	   public boolean updateBoard(HkDto dto) {
	      int count=0;
	      
	      SqlSession sqlSession=null;
	      
	      try {
	         sqlSession=getSqlSessionFactory().openSession(true);
	         count=sqlSession.update(namespace+"updateBoard",dto);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         sqlSession.close();
	      }
	      return count>0?true:false;
	   }
	   
	   //5.조회수
	   public boolean readCount(int seq) {
	      int count=0;
	      SqlSession sqlSession=null;
	      
	      try {
	         sqlSession=getSqlSessionFactory().openSession(true);
	         count=sqlSession.update(namespace+"readCount",seq);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return count>0?true:false;
	   }
	   
	   //6.삭제하기
	   public boolean mulDel(String[] seqs) {
	      int count=0;
	      SqlSession sqlSession=null;
	      Map<String,String[]>map=new HashMap<>();
	      map.put("seqs", seqs);
	      try {
	         sqlSession=getSqlSessionFactory().openSession(true);
	         count=sqlSession.update(namespace+"mulDel",map);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         sqlSession.close();
	      }
	      return count>0?true:false;
	   }
	   
	   //7. 답글 추가하기(update문,insert문 실행 -> transaction 처리 필요)
	   public boolean replyBoard(HkDto dto) {
	      int count=0;
	      SqlSession sqlSession=null;
	      try {
	         //               openSession()<-autocommit:false
	         //                     이유: transaction처리를 위해
	         sqlSession=getSqlSessionFactory().openSession(false);
	         //같은 그룹에서 부모의 step보다 큰 글들의 step+1을 해줌
	         //update문 실행
	         sqlSession.update(namespace+"replyUpdate",dto);
	         //insert문 실행
	         count=sqlSession.insert(namespace+"replyInsert",dto);
	         sqlSession.commit();   //쿼리가 정상처리됐다면 DB에 반영
	      } catch (Exception e) {
	         sqlSession.rollback();    //여러 작업 중 실패가 있다면 모두 되돌림
	         e.printStackTrace();
	      }finally {
	         sqlSession.close();
	      }
	      return count>0?true:false;
	   }
	   public void test() {
	      //쿼리를 실행시킬 수 있는 객체 :sqlSession객체 구함
	      SqlSession sqlSession=getSqlSessionFactory().openSession();
	      //쿼리를 실행한다.--> sqlMapper.xml에 있는 쿼리를 실행할거임
	      List<HkDao>list=sqlSession.selectList("boardList");
	   }
}











