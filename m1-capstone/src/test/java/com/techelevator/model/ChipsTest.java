package com.techelevator.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChipsTest {
	
	private Chip chips;
	
	@Before
	public void setup() {
		chips = new Chip("A2", "Pringles", 2.00);
	}
	
	@Test
	public void slotNumberTest() {
		Assert.assertEquals("A2", chips.getSlotNumber());
	}
	
	@Test
	public void priceTest() {
		Assert.assertEquals(2.00, chips.getPrice(), 0.1);
	}
	
	@Test
	public void dispenseTest() {
		Assert.assertEquals("Crunch Crunch, Yum!", chips.dispense());
	}
	
	@Test
	public void nameTest() {
		Assert.assertEquals("Pringles", chips.getName());

	}

}

