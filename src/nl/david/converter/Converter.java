package nl.david.converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import au.com.bytecode.opencsv.CSVReader;

public class Converter {
	List<Transaction> transactions;
	Map<String, Category> categories;

	protected Map<String, Category> getCategories() {
		return categories;
	}
	protected void setCategories(Map<String, Category> categories) {
		this.categories = categories;
	}
	protected List<Transaction> getTransactions() {
		return transactions;
	}
	protected void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public Converter() {
		this.transactions = new ArrayList<Transaction>();
		this.categories = new TreeMap<String, Category>();
	}

	public void readDataFile(String filename, AccountType type) {
		BufferedReader br = null;
		String line = "";
		String splitBy = "\t";

		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				String[] st = line.split(splitBy, 8);
				Transaction t = new Transaction(st[2], type, st[7], st[6]);
				this.transactions.add(t);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void readCategoriesFile(String filename) {
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(filename),',');
			String [] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Category c = null;
				if(nextLine[1].contains("," ))
					c = new Category(nextLine[0], "\"" + nextLine[1] + "\"", nextLine[2]);
				else
					c = new Category(nextLine[0], nextLine[1], nextLine[2]);
				this.categories.put(c.getKeyword(), c);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void translateCategory() {
		for(Transaction t : this.transactions) {
			String description = t.getDescription();
			for(String s : this.categories.keySet()) {
				if(description.toLowerCase().contains(s.toLowerCase())) {
					t.setCategory(this.categories.get(s).getName().trim());
					t.setDescription(this.categories.get(s).getDescription().trim());
				}
			}
		}
	}

	public void processTransfers() {
		List<Transaction> tempList = new ArrayList<Transaction>();
		for(Transaction t : this.transactions) {
			if(t.getCategory().compareTo("\"Transfer,Checking\"") == 0) {
				Transaction n = null;
				t.setCategory("Transfer");
				if(t.getAmount() > 0) {
					t.setDescription("Money transfer from Savings to ABN");
				} else {
					t.setDescription("Money transfer from ABN to Savings");
				}
				n = new Transaction(t.getDate(), AccountType.SAVINGS, t.getCategory(), t.getDescription(), t.getAmount()*-1);
				tempList.add(n);
			}
		}
		this.transactions.addAll(tempList);
	}

	public void writeImportFile() {
		PrintWriter writer = null;
		try {
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(Calendar.getInstance().getTime());
			writer = new PrintWriter("ImportFile-" + timeStamp + ".csv", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		writer.println("Date,Account Name,Category,Description,Amount,Check,Payee,Note");
		for(Transaction t : this.transactions) {
			t.printTransaction(writer);
		}
		writer.close();
	}

	public void printTransactions() {
		for(Transaction t : this.transactions) {
			t.printTransaction();
		}
	}

	public static void main(String[] args) {
		String dataFile = "";
		String referenceFile = "";

		if(args.length != 3) {
			System.out.println("ERROR! The syntax is: <data_file> <reference_file> {CHECKING | SAVINGS}");
			return;
		}

		dataFile = args[0];
		referenceFile = args[1];

		Converter conv = new Converter();
		if(args[2].toLowerCase().compareTo("savings") == 0)
            conv.readDataFile(dataFile, AccountType.SAVINGS);
        else
            conv.readDataFile(dataFile, AccountType.CHECKING);
		conv.readCategoriesFile(referenceFile);
		conv.translateCategory();
		//conv.processTransfers();
		conv.writeImportFile();
		System.out.println("File generated!");
	}
}

