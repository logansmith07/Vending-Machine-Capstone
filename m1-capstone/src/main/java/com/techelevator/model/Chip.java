package com.techelevator.model;

public class Chip extends Product implements Vendable, Consumable {
	
	private String slotNumber;
	private double price;
	
	public Chip(String slotNumber, String name, double price) {
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
		return "Crunch Crunch, Yum!";
	}
	
}
