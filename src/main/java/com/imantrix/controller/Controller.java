package com.imantrix.controller;


import java.util.Random;
import java.util.Scanner;

import com.imantrix.service.BankServiceImpl;
import com.imantrix.service.BankServiceIntf;
import com.imantrix.vo.User;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class Controller {

	public static void main(String[] args) {
		do {
		System.out.println("welcome to digital banking");
		System.out.println("1.new user");
		System.out.println("2.existing user");
		Controller controller = new Controller();
		User user = null;
		Scanner input = new Scanner(System.in);
			int key = input.nextInt();
			switch (key) {
			case 1:
				controller.inputUser();
				break;
			case 2:
				user = controller.fetchByCardno();
				controller.validatePassword(user);
				break;
			default:
				break;
			}
		} while (true);
	}

	public void validatePassword(User user) {
		System.out.println("welcome  " + user.getUserName() + "  to digital banking");
		System.out.println("Enter the password");
		Scanner input = new Scanner(System.in);
		String password = input.nextLine();
		if (password.equals(user.getPassword())) {
			System.out.println("password matches");
			Bank bank = new Bank();
			bank.banking(user);
		} else {
			System.out.println("password does not matches");
		}
	}
	
	public User fetchByCardno() {
		System.out.println("enter card no");
		Scanner input = new Scanner(System.in);
		int cardNo = input.nextInt();
		System.out.println("card no is :" + cardNo);
		BankServiceIntf bankServiceIntf = new BankServiceImpl();
		User user = bankServiceIntf.fetchUserByCardNo(cardNo);
		return user;
	}

	private void inputUser() {
		User user = new User();
		System.out.println("Enter the name");
		Scanner input = new Scanner(System.in);
		user.setUserName(input.nextLine());
		System.out.println("enter the password");
		user.setPassword(input.nextLine());
		Random random = new Random();
		user.setCardNo(random.nextInt(999));
		BankServiceIntf bankServiceIntf = new BankServiceImpl();
		int flag = bankServiceIntf.createUser(user);
		if (flag > 0)
			System.out.println("registerd successfully and card no is : " + user.getCardNo());
	}

}
