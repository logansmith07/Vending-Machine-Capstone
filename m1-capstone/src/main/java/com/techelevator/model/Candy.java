package com.techelevator.model;

public class Candy extends Product implements Vendable, Consumable {
	
	private String slotNumber;
	private double price;
	
	public Candy(String slotNumber, String name, double price) {
		this.name = name;
		this.slotNumber = slotNumber;
		this.price = price;
	}

	@Override
	public String getSlotNumber() {
		return slotNumber;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public String dispense() {
		return "Munch Munch, Yum!";
	}

}
