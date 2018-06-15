package com.techelevator.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransactionTest {
	
	private Transaction transaction;
	
	@Before
	public void setup() {
		transaction = new Transaction();
	}
	
	@Test
	public void depositTest() {
		transaction.deposit(100.01);
		Assert.assertEquals(100.01, transaction.getBalance(), .01);
	}
	
	@Test
	public void withdrawTest() {
		transaction.deposit(100.01);
		Assert.assertEquals(100.01, transaction.getBalance(), .01);
		transaction.withdraw();
		Assert.assertEquals(0, transaction.getBalance(), .01);
	}
	
	@Test
	public void purchaseTest() {
		Candy newCandy = new Candy("A4", "Skittles", 1.98);
		transaction.deposit(100.00);
		Assert.assertEquals(100.00, transaction.getBalance(), .01);
		transaction.purchase(newCandy);
		Assert.assertEquals(98.02, transaction.getBalance(), .01);
		Chip newChips = new Chip("C4", "Pringles", 3.02);
		transaction.purchase(newChips);
		Assert.assertEquals(95.00, transaction.getBalance(), .01);
	}
	
	
	

}
