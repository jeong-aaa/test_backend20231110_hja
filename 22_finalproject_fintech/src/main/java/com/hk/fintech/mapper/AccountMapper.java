package com.hk.fintech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hk.fintech.dtos.AccountDto;

@Mapper
public interface AccountMapper {

	void insertTransactionData(AccountDto adto);

	public List<AccountDto> TransactionDataList(String email, String yyyyMMdd);
}
