package com.techelevator.model;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	
	private double balance = 0.0;
				
	public double deposit(double amount) {
		commitChange("FEED MONEY:", amount);
		return balance;
	}
	
	public double purchase(Vendable item) {
		String description = item.getName()+ " " +item.getSlotNumber(); 
		commitChange(description, item.getPrice() * -1);
		return balance; 
	}
	
	public double withdraw() {
		double cash = balance;
		commitChange("GIVE CHANGE:", balance * -1);
		return cash;
	}

	public double getBalance() {
		return balance;
	}
	
	private void log(String action) {
		String filePath = "/Users/lsmith/Development/Pairs/team1-java-green-week4-pair-exercises/m1-capstone/src/main/resources";
		File newFile = new File(filePath, "log.txt");
		
		try {
			newFile.createNewFile();
			FileWriter fw = new FileWriter("log.txt", true);
		    fw.write(action);
		    fw.close();
		} catch (IOException error) {
			error.printStackTrace();
		}
	
	}
	
	private void commitChange(String description, double amountToChange) {
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		String oldBalance = currencyFormat.format(balance);
		balance += amountToChange;
		String newBalance = currencyFormat.format(balance);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
		String currentDate = formatter.format(new Date()).toUpperCase();
		String action = String.format("%-20s %-25s %-8s %-8s\n", currentDate, description, oldBalance, newBalance);
		log(action);
	}
	
	

}
