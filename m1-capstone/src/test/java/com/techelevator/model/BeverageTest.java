package com.techelevator.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BeverageTest {
	
	private Beverage beverage;
	
	@Before
	public void setup() {
		beverage = new Beverage("A4", "Coke", 2.50);
	}
	
	@Test
	public void slotNumberTest() {
		Assert.assertEquals("A4", beverage.getSlotNumber());
	}
	
	@Test
	public void priceTest() {
		Assert.assertEquals(2.50, beverage.getPrice(), 0.1);
	}
	
	@Test
	public void dispenseTest() {
		Assert.assertEquals("Glug Glug, Yum!", beverage.dispense());
	}
	
	@Test
	public void nameTest() {
		Assert.assertEquals("Coke", beverage.getName());

	}

}
