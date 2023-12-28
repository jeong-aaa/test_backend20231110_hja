package com.hk.fintech.service;

import java.util.List;
import java.util.Map;

import com.hk.fintech.command.InsertCalCommand;
import com.hk.fintech.dtos.CashDto;

import jakarta.servlet.http.HttpServletRequest;



public interface ICashService {
	
	//달력생성시 필요한 값 구하는 메서드
	public Map<String, Integer> makeCalendar(HttpServletRequest request);
	
	//일정 추가
	public boolean insertCalBoard(InsertCalCommand insertCalCommand) ;
	
	// 해당 날짜의 거래내역 조회
//    List<CashDto> getTransactionDataByDate(String yyyyMMdd);
	
	
//	//일정 목록
////	public List<CalDto> calBoardList(String yyyyMMdd);
//	public List<CalDto> checkinfoBydept(String yyyyMMdd );
//	//일정 상세조회
//	public CalDto calBoardDetail(int seq);
//	//일정 수정하기
//	public boolean calBoardUpdate(UpdateCalCommand updateCalCommand);
//	//일정 삭제하기
//	public boolean calMulDel(Map<String, String[]>map);
//	//한달의 일정보여주기
//	public List<CalDto> calViewList(String id, String yyyyMM);
//	//일일의 일정개수 보여주기
//	public int calBoardCount(String yyyyMMdd);
//
//	public List<CalDto> getAllList(String yyyyMMdd);

	public List<CashDto> Cash(String email, String yyyyMM);

}









