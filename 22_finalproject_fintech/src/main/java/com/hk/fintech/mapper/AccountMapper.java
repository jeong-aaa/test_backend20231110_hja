package com.hk.fintech.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hk.fintech.dtos.AccountDto;

@Mapper
public interface AccountMapper {

	public AccountDto transactionList(AccountDto adto);
}