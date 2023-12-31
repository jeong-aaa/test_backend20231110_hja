package com.hk.fintech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.fintech.dtos.AccountDto;
import com.hk.fintech.dtos.CashDto;


@Mapper
public interface CashMapper {
	
	//일정 추가
	public int addCash(CashDto dto);
	//일정 목록
//	public List<CalDto> calBoardList(String yyyyMMdd);
//	public List<CalDto> checkinfoBydept(String yyyyMMdd );
//	
//	//일정 상세조회
//	public CalDto calBoardDetail(int seq);
//	//일정 수정하기
//	public boolean calBoardUpdate(CalDto dto);
//	//일정 삭제하기
//	public boolean calMulDel(Map<String, String[]>map);
//	//한달의 일정 보여주기
//	public List<CalDto> calViewList(String id, String yyyyMM);
//	//일일의 일정개수 보여주기
//	public int calBoardCount(String yyyyMMdd);
//	
//	public List<CalDto> getAllList(String yyyyMM);
//	


//	public List<CashDto> getTransactionDataByDate(String yyyyMMdd);

	public List<CashDto> Cash(Map<String, String>map);
	
	public List<CashDto> cashDetailList(Map<String, String> map);
	
	public List<CashDto> cashsum(String useremail);
	
}









