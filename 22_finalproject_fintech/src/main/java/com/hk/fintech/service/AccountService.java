package com.hk.fintech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.fintech.dtos.AccountDto;
import com.hk.fintech.mapper.AccountMapper;

@Service
public class AccountService {
	
	@Autowired
	private AccountMapper accountMapper;

	@Transactional
	public void saveTransactionData(AccountDto adto) {
		accountMapper.insertTransactionData(adto);
	}
}
