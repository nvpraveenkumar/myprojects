package com.imantrix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.imantrix.vo.Transaction;
import com.imantrix.vo.User;

public class BankDaoImpl implements BankDaoIntf {

	@Override
	public User fetchUserNameByCardNo(int cardNo) {
		User user = new User();
		Connection conn = DBUtil.getConnection();
		Statement stmt;
		String name = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user where cardno =" + cardNo);
			while (rs.next()) {
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setCardNo(rs.getInt(4));
				System.out.println(user);
			}
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public int createUser(User user) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt;
		String name = null;
		try {
			stmt = conn.prepareStatement("insert into user values(default,?,?,?)");
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getCardNo());
			int i = stmt.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int depositMoney(Transaction transaction) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt;
		String name = null;
		try {
			stmt = conn.prepareStatement("insert into transaction values(default,?,?,?,?,?)");
			stmt.setInt(1, transaction.getUserId());
			stmt.setString(2, transaction.getDate());
			stmt.setInt(3, 0);
			stmt.setInt(4, transaction.getCredit());
			stmt.setInt(5, transaction.getBalance());
			System.out.println("running");
			int i = stmt.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int checkBalance(Transaction transaction) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt;
		String name = null;
		int balance = 0;
		try {
			stmt = conn.prepareStatement("SELECT balance FROM `transaction` where userid=? ORDER BY id DESC LIMIT 1");
			stmt.setInt(1, transaction.getUserId());
			ResultSet rs = stmt.executeQuery();
			// "SELECT balance FROM `transaction` where userid=1 ORDER BY id DESC LIMIT 1");

			while (rs.next()) {
				//System.out.println("balance is " + rs.getInt(1));
				balance=rs.getInt(1);
			}

			return balance;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int withdraw(Transaction transaction) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt;
		String name = null;
		try {
			stmt = conn.prepareStatement("insert into transaction values(default,?,?,?,?,?)");
			stmt.setInt(1, transaction.getUserId());
			stmt.setString(2, transaction.getDate());
			stmt.setInt(3, transaction.getDebit());
			stmt.setInt(4, 0);
			stmt.setInt(5, transaction.getBalance());
			System.out.println("running");
			int i = stmt.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public List<Transaction> displayBankStatement(int userId) {
		Connection conn = DBUtil.getConnection();
		List<Transaction> transactions =  new ArrayList();
		Statement stmt;
		String name = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select date,debit,credit,balance from transaction where userid =" + userId);
			System.out.println("date : debit : credit : balance ");
			while (rs.next()) {
				String date = rs.getString(1);
				int debit =rs.getInt(2);
				int credit = rs.getInt(3);
				int balance = rs.getInt(4);
				System.out.println(date+ " : "+debit+" : "+credit+" : "+balance);
				transactions.add(new Transaction(date,debit,credit,balance));
			}
			return transactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
