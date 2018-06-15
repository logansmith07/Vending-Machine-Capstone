package com.techelevator.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GumTest {
	
	private Gum gum;
	
	@Before
	public void setup() {
		gum = new Gum("A1", "Stride", 1.00);
	}
	
	@Test
	public void slotNumberTest() {
		Assert.assertEquals("A1", gum.getSlotNumber());
	}
	
	@Test
	public void priceTest() {
		Assert.assertEquals(1.00, gum.getPrice(), 0.1);
	}
	
	@Test
	public void dispenseTest() {
		Assert.assertEquals("Chew Chew, Yum!", gum.dispense());
	}
	
	@Test
	public void nameTest() {
		Assert.assertEquals("Stride", gum.getName());

	}

}
