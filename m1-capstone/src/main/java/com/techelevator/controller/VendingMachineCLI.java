package com.techelevator.controller;

import java.text.NumberFormat;

import com.techelevator.model.Consumable;
import com.techelevator.model.Vendable;
import com.techelevator.model.VendingMachine;
import com.techelevator.view.InventoryList;
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													   MAIN_MENU_OPTION_PURCHASE,
													   MAIN_MENU_OPTION_EXIT};
	
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
															PURCHASE_MENU_OPTION_SELECT_PRODUCT,
															PURCHASE_MENU_OPTION_FINISH};
	
	private Menu menu;
	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	
	public void run() {
		VendingMachine vendingMachine = new VendingMachine("vendingmachine.csv", 5);
		
		while(true) {
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if(choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				InventoryList.createList(vendingMachine.getAllItems(), vendingMachine.soldOutItems());
			} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while(true) {
					NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
					System.out.println();
					System.out.println("Current Money Provided: " + currencyFormat.format(vendingMachine.currentBalance()));
					
					String purchaseChoice = (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					
					if(purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						
						double feedAmount = menu.getMoneyFromUser("Enter the amount to feed into machine: ");
						vendingMachine.feedMoney(feedAmount);
						
					} else if(purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						
						InventoryList.createList(vendingMachine.getAllItems(), vendingMachine.soldOutItems());
						String slot = menu.getStringFromUser("Enter the slot to vend: ").toUpperCase();
						boolean slotExists = vendingMachine.getAllItems().containsKey(slot);
						
						if (slotExists) {
							Vendable vendedItem = vendingMachine.vend(slot);
							if (vendedItem != null) {
								
								System.out.println();
								System.out.println("Name: " + vendedItem.getName() + ", Price: " + currencyFormat.format(vendedItem.getPrice()) + ", New Balance: " + currencyFormat.format(vendingMachine.currentBalance()));
								
								Consumable consumedItem = (vendedItem instanceof Consumable) ? (Consumable)vendedItem : null;
								if (consumedItem != null) {
									System.out.println();
									System.out.println(consumedItem.dispense());
								}
								
							} else {
								if(vendingMachine.soldOutItems().containsKey(slot)) {
									System.out.println();
									System.out.println("Item is sold out!");
								} else {
									System.out.println();
									System.out.println("There is not enough money provided! Please provide more money!");
								}
							}
						} else {
							System.out.println();
							System.out.println("Slot does not exist!");
						}
						
					} else if(purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH)) {
						int[] change = vendingMachine.returnChange();
						System.out.println();
						System.out.println("Returned Change: " + change[0] + " Quarter(s), " + change[1] + " Dime(s) & " + change[2] + " Nickel(s)!");
						break;
					}
				}
				
			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
			}
		}
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
	
	public void showPurchaseScreen() {
		
	}
}
