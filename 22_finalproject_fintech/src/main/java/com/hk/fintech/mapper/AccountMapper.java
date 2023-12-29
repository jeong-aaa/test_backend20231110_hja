package com.hk.fintech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hk.fintech.dtos.AccountDto;

@Mapper
public interface AccountMapper {

	void insertTransactionData(AccountDto adto);

	public List<AccountDto> TransactionDataList(Map<String, String> map);
	
	List<AccountDto> Account(Map<String, String> map);

	//
	void deleteTransactionData(String fintech_use_num);

	void deleteAccount(String fintech_use_num);
}
