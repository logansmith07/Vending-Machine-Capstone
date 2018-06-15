package com.techelevator.view;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import com.techelevator.model.Vendable;

public class InventoryList {
	
	public static void createList(Map<String, List<Vendable>> listItems, Map<String, Vendable> soldOutItems) {
		System.out.println();
		System.out.println("VENDING MACHINE ITEMS");
		System.out.println();
		System.out.printf("%-5s %-8s %-20s %-8s\n", "Slot", "Price", "Name", "Left");
		System.out.println("-----------------------------------------");
		for (String slot : listItems.keySet()) {
			List<Vendable> listItem = listItems.get(slot);
			int itemCount = listItem.size();
			if (itemCount < 1) {
				Vendable soldOutItem = soldOutItems.get(slot);
				System.out.printf("%-5s %-8s %-20s %-15s\n", slot, soldOutItem.getPrice(), soldOutItem.getName(), "SOLD OUT!");
				continue;
			}
			Vendable item = listItem.get(0);
			String itemName = item.getName();
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
			String itemPrice = currencyFormat.format(item.getPrice());
			System.out.printf("%-5s %-8s %-20s %-15s\n", slot, itemPrice, itemName, itemCount);
		}
	}
	
}
