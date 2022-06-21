package com.imantrix.vo;

public class Transaction {

	private int id;
	private int userId;
	private int credit;
	private int debit;
	private int balance;
	private String date;
	
	
	

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(String date, int debit, int credit, int balance) {
		this.date=date;
		this.debit=debit;
		this.credit=credit;
		this.balance=balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getDebit() {
		return debit;
	}
	public void setDebit(int debit) {
		this.debit = debit;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", userId=" + userId + ", credit=" + credit + ", debit=" + debit + ", balance="
				+ balance + ", date=" + date + "]";
	}
	
	

	
}
