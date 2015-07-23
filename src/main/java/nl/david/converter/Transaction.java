package nl.david.converter;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Transaction {
    Date date;
    AccountType accountName;
	String category;
	String description;
	Double amount;
	String check;
	String payee;
	String note;
	
	protected Transaction() {
		// TODO Auto-generated constructor stub
	}
	protected Transaction(Date date, AccountType accountName, String category, String description, Double amount) {
		this.date = date;
		this.accountName = accountName;
		this.category = category;
		this.description = description;
		this.amount = amount;
		this.check = "";
		this.payee = "";
		this.note = "";
	}
	protected Transaction(String date, AccountType accountName, String description, String amount) throws ParseException {
		this.accountName = accountName;
		this.category = "";
		this.description = description;
		this.amount = Double.parseDouble(amount.replace(',', '.'));
		this.check = "";
		this.payee = "";
		this.note = "";
		
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMdd");
		this.date = d.parse(date);
	}
	protected String printDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(this.date);
	}
	protected void printTransaction() {
		System.out.println(this.printDate() + ", " + this.accountName + ", " + this.category + ", " + this.description + ", " + this.amount
				 + ", " + this.check + ", " + this.payee + ", " + this.note);
	}
	protected void printTransaction(PrintWriter writer) {
		writer.println(this.printDate() + "," + this.accountName.getName() + "," + this.category + "," + this.description + "," + this.amount
				 + "," + this.check + "," + this.payee + "," + this.note);
	}
	protected AccountType getAccountName() {
		return accountName;
	}
	protected void setAccountName(AccountType accountName) {
		this.accountName = accountName;
	}
	protected Double getAmount() {
		return amount;
	}
	protected void setAmount(Double amount) {
		this.amount = amount;
	}
	protected String getCategory() {
		return category;
	}
	protected void setCategory(String category) {
		this.category = category;
	}
	protected String getCheck() {
		return check;
	}
	protected void setCheck(String check) {
		this.check = check;
	}
	protected Date getDate() {
		return date;
	}
	protected void setDate(Date date) {
		this.date = date;
	}
	protected String getDescription() {
		return description;
	}
	protected void setDescription(String description) {
		this.description = description;
	}
	protected String getNote() {
		return note;
	}
	protected void setNote(String note) {
		this.note = note;
	}
	protected String getPayee() {
		return payee;
	}
	protected void setPayee(String payee) {
		this.payee = payee;
	}
}
