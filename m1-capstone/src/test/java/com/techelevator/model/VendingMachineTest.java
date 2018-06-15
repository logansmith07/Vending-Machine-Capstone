package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {
	
	private VendingMachine machine;
	
	@Before
	public void setup() {
		machine = new VendingMachine();
	}
	
	@Test
	public void emptyConstructorTest() {
		Assert.assertNotNull("Vending Machine should not be null.", machine);
	}
	
	@Test 
	public void constructorTest() {
		List<Vendable> products = new ArrayList<>();
		products.add(new Chip("A1", "Pringles", 2.00));
		products.add(new Candy("A2", "Skittles", 1.50));
		machine = new VendingMachine(products);
		Assert.assertEquals(2, machine.getAllItems().size());
	}
	
	@Test 
	public void constructorDataTest() {
		machine = new VendingMachine("vendingmachine.csv", 5);
		Assert.assertEquals(16, machine.getAllItems().size());
	}
	
	@Test
	public void feedMoneyTest() {
		machine.feedMoney(20.00);
		machine.feedMoney(14.32);
		Assert.assertEquals(34.32, machine.currentBalance(), 0.1);
	}
	
	@Test
	public void vendTest() {
		List<Vendable> products = new ArrayList<>();
		Chip newChip = new Chip("A1", "Pringles", 2.00);
		Candy newCandy = new Candy("A2", "Skittles", 1.50);
		products.add(newChip);
		products.add(newCandy);
		machine = new VendingMachine(products);
		machine.feedMoney(20.00);
		Assert.assertEquals(20.00, machine.currentBalance(), 0.1);
		Assert.assertEquals(newChip, machine.vend("A1"));
		Assert.assertEquals(newCandy, machine.vend("A2"));
		Assert.assertEquals(null, machine.vend("A7"));
		Assert.assertEquals(0, machine.getAllItems().get("A1").size());
	}
	
	@Test
	public void returnChangeTest() {
		machine.feedMoney(10);
		Assert.assertEquals(40, machine.returnChange()[0]);
		
	}
	
	@Test
	public void replenishTest() {
		machine.replenish(new Candy("A1", "Swedish Fish", 1.50));
		Assert.assertEquals(1, machine.getAllItems().get("A1").size());
	}

}
