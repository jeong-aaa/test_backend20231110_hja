package com.hk.fintech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.fintech.mapper.AccountMapper;

@Service
public class AccountService {
	
	@Autowired
	private AccountMapper accountMapper;
}
