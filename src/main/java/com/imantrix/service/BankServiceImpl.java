package com.imantrix.service;

import java.util.List;

import com.imantrix.dao.BankDaoImpl;
import com.imantrix.dao.BankDaoIntf;
import com.imantrix.vo.Transaction;
import com.imantrix.vo.User;

public class BankServiceImpl implements BankServiceIntf {

	@Override
	public User fetchUserByCardNo(int cardNo) {
		BankDaoIntf bankDaoIntf = new BankDaoImpl();
		User user = bankDaoIntf.fetchUserNameByCardNo(cardNo);
		return user;
	}

	@Override
	public int createUser(User user) {
		BankDaoIntf bankDaoIntf = new BankDaoImpl();
		int flag = bankDaoIntf.createUser(user);
		return flag;
	}

	@Override
	public int deposit(Transaction transaction) {
		BankDaoIntf bankDaoIntf = new BankDaoImpl();
		int flag = bankDaoIntf.depositMoney(transaction);
		return flag;
	}

	@Override
	public int checkBalance(Transaction transaction) {
		BankDaoIntf bankDaoIntf = new BankDaoImpl();
		int balance = bankDaoIntf.checkBalance(transaction);
		return balance;
	}

	@Override
	public int withdraw(Transaction transaction) {
		BankDaoIntf bankDaoIntf = new BankDaoImpl();
		int flag = bankDaoIntf.withdraw(transaction);
		return flag;
	}

	@Override
	public List<Transaction> bankStatement(int userId) {
		BankDaoIntf bankDaoIntf = new BankDaoImpl();
		List<Transaction> transactions = bankDaoIntf.displayBankStatement(userId);
		return transactions;
	}

}
