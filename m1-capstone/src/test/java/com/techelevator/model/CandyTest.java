package com.techelevator.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CandyTest {
	
	private Candy candy;
	
	@Before
	public void setup() {
		candy = new Candy("A3", "Airheads", 1.50);
	}
	
	@Test
	public void slotNumberTest() {
		Assert.assertEquals("A3", candy.getSlotNumber());
	}
	
	@Test
	public void priceTest() {
		Assert.assertEquals(1.50, candy.getPrice(), 0.1);
	}
	
	@Test
	public void dispenseTest() {
		Assert.assertEquals("Munch Munch, Yum!", candy.dispense());
	}
	
	@Test
	public void nameTest() {
		Assert.assertEquals("Airheads", candy.getName());

	}

}

