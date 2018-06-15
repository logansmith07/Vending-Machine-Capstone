package com.techelevator.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VendingMachine {
	
	private Store items;
	private Transaction currentTransaction = null;
	
	public VendingMachine() {
		items = new Store();
	}
	
	public VendingMachine(List<Vendable> products) {
		items = new Store(products);
	}
	
	public VendingMachine(String fileName, int stockPerItem) {
		List<Vendable> products = new ArrayList<>();
		BufferedReader bufferedReader;
		String line;
		try {
	        bufferedReader = new BufferedReader(new FileReader(fileName));
	        try {
	            while((line = bufferedReader.readLine()) != null) {
	            	String[] splitLine = line.split("\\|");
	            	
	            	double price = Double.parseDouble(splitLine[2]);
		            for (int i = 0; i < stockPerItem; i++) {
		            	if (splitLine[3].equals("Chip")) {
		            		Chip newChip = new Chip(splitLine[0], splitLine[1], price);
		            		products.add(newChip);
		            	} else if (splitLine[3].equals("Candy")) {
		            		Candy newCandy = new Candy(splitLine[0], splitLine[1], price);
		            		products.add(newCandy);
		            	} else if (splitLine[3].equals("Drink")) {
		            		Beverage newDrink = new Beverage(splitLine[0], splitLine[1], price);
		            		products.add(newDrink);
		            	} else if (splitLine[3].equals("Gum")) {
		            		Gum newGum = new Gum(splitLine[0], splitLine[1], price);
		            		products.add(newGum);
		            	}
	            	}
	            }
	            bufferedReader.close();
	        } catch (IOException e) {		            
	            e.printStackTrace();
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		items = new Store(products);
	}
	
	public Map<String, List<Vendable>> getAllItems() {
		return items.getItems();
	}
	
	public Map<String, Vendable> soldOutItems() {
		return items.getSoldOutItems();
	}
	
	public double currentBalance() {
		if(currentTransaction == null) {
			return 0;
		}
		
		return currentTransaction.getBalance();
	}
	
	public double feedMoney(double amount) {
		if(currentTransaction == null) {
			currentTransaction = new Transaction();
		}
		return currentTransaction.deposit(amount);
	}
	
	public Vendable vend(String slotNumber) {
		Vendable item = items.getItem(slotNumber);
		if(item != null && currentBalance() >= item.getPrice()) {
			items.removeItem(slotNumber);
			currentTransaction.purchase(item);
			return item;
		}
		return null;
	}
	
	public int[] returnChange() {
		if(currentTransaction == null) {
			int[] emptyChange = {0, 0, 0};
			return emptyChange;
		}
		double withdrawnAmount = currentTransaction.withdraw();
		int[] change = calculateChange(withdrawnAmount);
		currentTransaction = null;
		return change;
	}
	
	public void replenish(Vendable item) {
		items.addItem(item);
	}
	
	private int[] calculateChange(double amount) {
		int nickels = 0;
		int dimes = 0;
		int quarters = 0;
		if(amount > 0) {
			amount *= 100;
			quarters = (int)Math.floor(amount / 25);
			amount %= 25;
		}
		if (amount > 0) {
			dimes = (int)Math.floor(amount / 10);
			amount %= 10;
		}
		if (amount > 0) {
			nickels = (int)Math.floor(amount / 5);
		}
		int[] changeValues = {quarters, dimes, nickels};
		return changeValues;	
		
	}
	
	

}
