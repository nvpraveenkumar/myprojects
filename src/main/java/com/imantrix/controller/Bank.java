package com.imantrix.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.imantrix.service.BankServiceImpl;
import com.imantrix.service.BankServiceIntf;
import com.imantrix.vo.Transaction;
import com.imantrix.vo.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Bank {

	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void banking(User user) {
		Scanner input = new Scanner(System.in);
		char ch = 0;
		Transaction transaction = new Transaction();
		long aTotalAmount = 0;
		do {
			System.out.println("1.check balance");
			System.out.println("2.deposit");
			System.out.println("3.withdraw");
			System.out.println("4.bank statement");
			System.out.println("5.excel download statement");
			System.out.println("6.pdf download");
			System.out.println("7.logout");
			int key = input.nextInt();
			switch (key) {
			case 1:
				System.out.println("show bank balance");
				transaction.setUserId(user.getId());
				int balance = checkBalance(transaction);
				System.out.println("balance is : " + balance);
				break;
			case 2:
				transaction.setUserId(user.getId());
				depositAmount(transaction);
				break;
			case 3:
				transaction.setUserId(user.getId());
				debitAmount(transaction);
				break;
			case 4:
				int userid = user.getId();
				displayStatement(userid);
				break;
			case 5:
				System.out.println("excel file download");
				// excelDownload();
				excelStatement(user.getId());
				break;
			case 6:
				System.out.println("pdf file dowload");
				pdfDownload(user.getId());
				System.out.println("pdf file created");
				break;
			case 7:
				ch = 'n';
				break;
			default:
				break;
			}
			if (ch == 'n') {
				System.out.println("thank you for banking");
				System.out.println("----------------------------------------------------");
				break;
			}
			ch = 'y';
			System.out.println("----------------------------------------------------");
		} while (ch == 'y');

	}

	private void pdfDownload(int userId) {
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("/home/praveen/Music/pdfFile/helloword.pdf"));
			document.open();
			document.add(new Paragraph("Date     :  debit  :  credit :  balance"));
			BankServiceIntf bankServiceIntf = new BankServiceImpl();
			List<Transaction> transactions = bankServiceIntf.bankStatement(userId);
			for (Transaction transaction : transactions) {
				document.add(new Paragraph(transaction.getDate()+"        :  "+transaction.getDebit()+"  :  "+transaction.getCredit()+"  :  "+transaction.getBalance()));
			/*	HSSFRow row = sheet.createRow((short) i);
				 inserting data in the first row
				row.createCell(0).setCellValue(transaction.getDate());
				row.createCell(1).setCellValue(transaction.getDebit());
				row.createCell(2).setCellValue(transaction.getCredit());
				row.createCell(3).setCellValue(transaction.getBalance());
			*/	System.out.println(transaction);
			}
			document.close();
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void excelStatement(int userId) {
		try {
			// declare file name to be create
			String filename = "/home/praveen/Music/excelFile/Balance.xlsx";
			// creating an instance of HSSFWorkbook class
			HSSFWorkbook workbook = new HSSFWorkbook();
			// invoking creatSheet() method and passing the name of the sheet to be created
			HSSFSheet sheet = workbook.createSheet("balance");
			// creating the 0th row using the createRow() method
			HSSFRow rowhead = sheet.createRow((short) 0);
			// creating cell by using the createCell() method and setting the values to the
			// cell by using the setCellValue() method
			rowhead.createCell(0).setCellValue("Date");
			rowhead.createCell(1).setCellValue("Debit");
			rowhead.createCell(2).setCellValue("credit");
			rowhead.createCell(3).setCellValue("balance");
			// creating the 1st row
			BankServiceIntf bankServiceIntf = new BankServiceImpl();
			List<Transaction> transactions = bankServiceIntf.bankStatement(userId);
			int i = 1;
			for (Transaction transaction : transactions) {
				HSSFRow row = sheet.createRow((short) i);
				// inserting data in the first row
				row.createCell(0).setCellValue(transaction.getDate());
				row.createCell(1).setCellValue(transaction.getDebit());
				row.createCell(2).setCellValue(transaction.getCredit());
				row.createCell(3).setCellValue(transaction.getBalance());
				i = i + 1;
				System.out.println(transaction);
			}
			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			// closing the Stream
			fileOut.close();
			// closing the workbook
			workbook.close();
			// prints the message on the console
			System.out.println("Excel file has been generated successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void excelDownload() {
		// creating an instance of Workbook class
		Workbook wb = new HSSFWorkbook();
		// creates an excel file at the specified location
		OutputStream fileOut;
		try {
			fileOut = new FileOutputStream("/home/praveen/Music/excelFile/BankStatement.xlsx");
			System.out.println("Excel File has been created successfully.");
			wb.write(fileOut);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void displayStatement(int userId) {
		BankServiceIntf bankServiceIntf = new BankServiceImpl();
		List<Transaction> transactions = bankServiceIntf.bankStatement(userId);
		for (Transaction transaction : transactions) {
			System.out.println(transaction);
		}

	}

	private int checkBalance(Transaction transaction) {
		BankServiceIntf bankServiceIntf = new BankServiceImpl();
		int balance = bankServiceIntf.checkBalance(transaction);
		return balance;
	}

	private void debitAmount(Transaction transaction) {
		System.out.println("withdraw");
		System.out.println("enter the amount");
		Scanner input = new Scanner(System.in);
		int amount = input.nextInt();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		System.out.println(strDate);
		int balance = checkBalance(transaction);
		int totalAmount = balance - amount;
		if (totalAmount < 0) {
			System.out.println("shortage of money");
		} else {
			transaction.setDate(strDate);
			transaction.setDebit(amount);
			transaction.setBalance(totalAmount);
			System.out.println(transaction);
			BankServiceIntf bankServiceIntf = new BankServiceImpl();
			int flag = bankServiceIntf.withdraw(transaction);
			if (flag > 0)
				System.out.println("money withdraw successfully");
		}

	}

	private void depositAmount(Transaction transaction) {
		System.out.println("deposit");
		System.out.println("enter the amount");
		Scanner input = new Scanner(System.in);
		int amount = input.nextInt();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		System.out.println(strDate);
		transaction.setDate(strDate);
		transaction.setCredit(amount);
		int balance = checkBalance(transaction);
		int totalAmount = balance + amount;
		transaction.setBalance(totalAmount);
		System.out.println(transaction);
		BankServiceIntf bankServiceIntf = new BankServiceImpl();
		int flag = bankServiceIntf.deposit(transaction);
		if (flag > 0)
			System.out.println("money deposited successfully");
	}

}
