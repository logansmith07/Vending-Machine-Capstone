package com.techelevator.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Store {
	
	private Map<String, List<Vendable>> items = new LinkedHashMap<>();
	private Map<String, Vendable> soldOutItems = new LinkedHashMap<>();

	public Store() {}
	
	public Store(List<Vendable> items) {
		storeItems(items);
	}
	
	private void storeItems(List<Vendable> itemsToAdd) {
		for(Vendable item : itemsToAdd) {
			List<Vendable> slot = items.get(item.getSlotNumber());
			if(slot == null) {
				List<Vendable> newList = new ArrayList<>();
				newList.add(item);
				items.put(item.getSlotNumber(), newList);
			}else if(slot.size() < 5) {
				slot.add(item);
			}
		}
	}
	
	public Vendable getItem(String slotNumber) {
		List<Vendable> slotList = items.get(slotNumber);
		
		if(slotList == null || slotList.size() < 1) {
			return null;
		}
		return items.get(slotNumber).get(0);
	}
	
	public void addItem(Vendable item) {
		List<Vendable> itemList = new ArrayList<>();
		
		List<Vendable> slotList = items.get(item.getSlotNumber());
		if (slotList != null && slotList.size() == 0) {
			soldOutItems.remove(item.getSlotNumber());
		}
		
		itemList.add(item);
		
		storeItems(itemList);
	}
	
	public boolean removeItem(String slotNumber) {
		List<Vendable> itemList = items.get(slotNumber);
		if(itemList == null || itemList.size() < 1) {
			return false;
		}
		
		if(itemList.size() == 1) {
			soldOutItems.put(slotNumber, itemList.get(0));
		}
		
		itemList.remove(0);
		
		return true;
	}
	
	public void clearStore() {
		items.clear();
	}
	
	public Map<String, List<Vendable>> getItems() {
		return items;
	}
	
	public Map<String, Vendable> getSoldOutItems() {
		return soldOutItems;
	}

}
