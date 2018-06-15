package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}
	
	public double getMoneyFromUser(String message) {
		double convertedAmount = 0;
		while(true) {
			try {
				System.out.println();
				System.out.print(message);
				String enteredAmount = in.nextLine();
				convertedAmount = Double.parseDouble(enteredAmount);
				if (convertedAmount != 1.0 && convertedAmount != 2.0 && convertedAmount != 5.0 && convertedAmount != 10.0 && convertedAmount != 20.0) {
					System.out.println();
					System.out.println("We only accept $1, $2, $5, $10 & $20 bills!");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println();
				System.out.println("Invalid amount!");
				continue;
			}
		}
		return convertedAmount;
	}
	
	public String getStringFromUser(String message) {
		System.out.println();
		System.out.print(message);
		String input = in.nextLine();
		return input;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for(int i = 0; i < options.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
}
