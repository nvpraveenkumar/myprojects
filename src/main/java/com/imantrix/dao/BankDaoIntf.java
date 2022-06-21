package com.imantrix.dao;

import java.util.List;

import com.imantrix.vo.Transaction;
import com.imantrix.vo.User;

public interface BankDaoIntf {

	 User fetchUserNameByCardNo(int cardNo);

	int createUser(User user);

	int depositMoney(Transaction transaction);

	int checkBalance(Transaction transaction);

	int withdraw(Transaction transaction);

	List<Transaction> displayBankStatement(int userId);

	

}
