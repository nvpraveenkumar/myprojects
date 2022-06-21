package com.imantrix.service;

import java.util.List;

import com.imantrix.vo.Transaction;
import com.imantrix.vo.User;

public interface BankServiceIntf {

	User fetchUserByCardNo(int cardNo);

	int createUser(User user);

	int deposit(Transaction transaction);

	int checkBalance(Transaction transaction);

	int withdraw(Transaction transaction);

	List<Transaction> bankStatement(int userId);

}
