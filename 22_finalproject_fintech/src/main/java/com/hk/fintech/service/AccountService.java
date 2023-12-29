package com.hk.fintech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.fintech.dtos.AccountDto;
import com.hk.fintech.dtos.UserDto;
import com.hk.fintech.mapper.AccountMapper;

@Service
public class AccountService {
	
	@Autowired
	private AccountMapper accountMapper;

	@Transactional
	public void saveTransactionData(AccountDto adto) {
		accountMapper.insertTransactionData(adto);
	}

	//
	@Transactional
    public void deleteAccount(String fintech_use_num) {

        // 계좌 관련 데이터 삭제 예시
        accountMapper.deleteTransactionData(fintech_use_num);

        // 계좌 삭제 예시
        accountMapper.deleteAccount(fintech_use_num);
    }


	
	public List<AccountDto> TransactionDataList(String email,String yyyyMMdd){
		Map<String, String>map=new HashMap<String, String>();
		map.put("useremail", email);
		map.put("tran_date", yyyyMMdd);
		return accountMapper.TransactionDataList(map);
	}

}








