package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StoreTest {
	
	private Store store;
	
	@Before
	public void setup() {
		store = new Store();
	}
	
	@Test
	public void emptyConstructorTest() {
		Map<String, List<Vendable>> items = store.getItems();
		Assert.assertEquals(0, items.size());
	}
	
	@Test
	public void constructorTest() {
		List<Vendable> items = new ArrayList<>();
		items.add(new Chip("A1", "Sun Chips", 3.00));
		items.add(new Gum("A2", "Extra", 1.00));
		items.add(new Candy("A3", "Starbursts", 1.50));
		items.add(new Chip("A1", "Sun Chips", 3.00));
		items.add(new Chip("A1", "Sun Chips", 3.00));
		store = new Store(items);
		Map<String, List<Vendable>> allItems = store.getItems();
		Chip newChips = new Chip("A1", "Sun Chips", 3.00);
		Gum newGum = new Gum("A2", "Extra", 1.00);
		Candy newCandy = new Candy("A3", "Starbursts", 1.50);
		Assert.assertEquals(3, allItems.get(newChips.getSlotNumber()).size());
		Assert.assertEquals(1, allItems.get(newGum.getSlotNumber()).size());
		Assert.assertEquals(1, allItems.get(newCandy.getSlotNumber()).size());
		Assert.assertEquals(3, allItems.size());
	}
	
	@Test
	public void addItemTest() {
		Chip newChips = new Chip("A3", "Starbursts", 1.50);
		store.addItem(newChips);
		Map<String, List<Vendable>> allItems = store.getItems();
		Assert.assertEquals(1, allItems.get(newChips.getSlotNumber()).size());
		Beverage newBeverage = new Beverage("A4", "Pepsi", 2.00);
		store.addItem(newBeverage);
		Map<String, List<Vendable>> allItems2 = store.getItems();
		Assert.assertEquals(1, allItems2.get(newBeverage.getSlotNumber()).size());
	}
	
	@Test
	public void removeItemTest() {
		Chip newChips = new Chip("A1", "Sun Chips", 3.00);
		store.addItem(newChips);
		Map<String, List<Vendable>> allItems = store.getItems();
		Assert.assertEquals(1, allItems.get(newChips.getSlotNumber()).size());
		store.removeItem(newChips.getSlotNumber());
		Map<String, List<Vendable>> allItems2 = store.getItems();
		Assert.assertEquals(0, allItems2.get(newChips.getSlotNumber()).size());
	}
	
	@Test
	public void clearItemsTest() {
		Chip newChips = new Chip("A1", "Sun Chips", 3.00);
		store.addItem(new Chip("A1", "Sun Chips", 3.00));
		Map<String, List<Vendable>> allItems = store.getItems();
		Assert.assertEquals(1, allItems.get(newChips.getSlotNumber()).size());
		store.clearStore();
		Assert.assertEquals(0, store.getItems().size());
	}
	

}
